-- ========================================
-- Football Hub 建表语句
-- ========================================

CREATE DATABASE IF NOT EXISTS football_hub
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;

USE football_hub;

DROP TABLE IF EXISTS `match`;

CREATE TABLE `match` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `league` varchar(100) NOT NULL COMMENT '联赛名称',
  `home_team` varchar(200) NOT NULL COMMENT '主队名称',
  `away_team` varchar(200) NOT NULL COMMENT '客队名称',
  `home_score` int DEFAULT NULL COMMENT '主队比分',
  `away_score` int DEFAULT NULL COMMENT '客队比分',
  `status` varchar(20) NOT NULL COMMENT '比赛状态: FINISHED/ONGOING/UPCOMING',
  `minute` varchar(10) DEFAULT NULL COMMENT '进行时长',
  `match_time` varchar(50) NOT NULL COMMENT '比赛时间',
  `venue` varchar(200) DEFAULT NULL COMMENT '比赛场地',
  `images` text DEFAULT NULL COMMENT '轮播图URL, $分隔',
  `create_time` int unsigned NOT NULL COMMENT '创建时间, 秒级时间戳',
  `update_time` int unsigned NOT NULL COMMENT '更新时间, 秒级时间戳',
  `is_deleted` tinyint DEFAULT 0 COMMENT '逻辑删除: 0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='比赛信息表';

INSERT INTO `match` (
  league, home_team, away_team, home_score, away_score,
  `status`, minute, match_time, venue, images
) VALUES (
  'PREMIER LEAGUE',
  'Arsenal',
  'Chelsea',
  3,
  1,
  'FINISHED',
  NULL,
  'Jul 5, 2026 20:00',
  'Emirates Stadium',
  'https://picsum.photos/seed/match1a/800/450$https://picsum.photos/seed/match1b/800/450$https://picsum.photos/seed/match1c/800/450'
);
