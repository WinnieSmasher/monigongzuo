# 赛事APP

baseUrl: http://localhost:8080

### 1、比赛列表

/match/list

参数：

返回：

```json
{
    list: [
        {
            id: [Long],
            league: [String],
            homeTeam: [String],
            awayTeam: [String],
            homeScore: [Int],
            awayScore: [Int],
            status: [String],
            minute: [String],
            matchTime: [String]
        }
    ]
}
```

### 2、比赛详情

/match/info

参数：

matchId:[Long]

返回：

```json
{
    id: [Long],
    league: [String],
    homeTeam: [String],
    awayTeam: [String],
    homeScore: [Int],
    awayScore: [Int],
    status: [String],
    minute: [String],
    matchTime: [String],
    venue: [String],
    images: [String[]]
}
```

# 赛事CONSOLE

baseUrl: http://localhost:8081

### 1、比赛新增

/match/create

参数：

images:[String]

league:[String]

homeTeam:[String]

awayTeam:[String]

homeScore:[Int]

awayScore:[Int]

status:[String]

minute:[String]

matchTime:[String]

venue:[String]

返回：

成功 or 失败

### 2、比赛修改

/match/update

参数：

matchId:[Long]

images:[String]

league:[String]

homeTeam:[String]

awayTeam:[String]

homeScore:[Int]

awayScore:[Int]

status:[String]

minute:[String]

matchTime:[String]

venue:[String]

返回：

成功 or 失败

### 3、比赛删除

/match/delete

参数：

matchId:[Long]

返回：

成功 or 失败

# 数据库设计

```sql
CREATE TABLE `match` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `images` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '轮播图，$拼接',
  `league` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联赛名称',
  `home_team` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主队名称',
  `away_team` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客队名称',
  `home_score` int DEFAULT NULL COMMENT '主队进球数，未开始为NULL',
  `away_score` int DEFAULT NULL COMMENT '客队进球数，未开始为NULL',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '比赛状态：LIVE / FINISHED / SCHEDULED',
  `minute` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '比赛进行分钟，仅LIVE有值',
  `match_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '比赛时间',
  `venue` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '比赛球场',
  `create_time` int unsigned NOT NULL,
  `update_time` int unsigned NOT NULL,
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_league` (`league`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='比赛信息表'
```
