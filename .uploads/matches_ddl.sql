-- ========================================
-- Football Hub 建表语句
-- ========================================

CREATE DATABASE IF NOT EXISTS football_hub
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;

USE football_hub;

DROP TABLE IF EXISTS `match`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='比赛信息表';

-- 插入示例数据
INSERT INTO `match` (
    images, league, home_team, away_team, home_score, away_score,
    `status`, minute, match_time, venue,
    create_time, update_time, is_deleted
) VALUES (
    'https://picsum.photos/seed/match1a/800/450$https://picsum.photos/seed/match1b/800/450$https://picsum.photos/seed/match1c/800/450',
    'PREMIER LEAGUE · 英超联赛',
    'Arsenal · 阿森纳',
    'Chelsea · 切尔西',
    3,
    1,
    'FINISHED',
    NULL,
    'Jul 5, 2026 20:00',
    'Emirates Stadium · 酋长球场',
    UNIX_TIMESTAMP(),
    UNIX_TIMESTAMP(),
    0
);
