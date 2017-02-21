/*
 Source Server         : localhost
 Source Server Type    : PostgreSQL
 Source Server Version : 90601
 Source Host           : localhost
 Source Database       : data_discovery
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90601
 File Encoding         : utf-8

 Date: 02/20/2017 10:49:11 AM
*/

-- ----------------------------
--  Table structure for file_status
-- ----------------------------
CREATE TABLE IF NOT EXISTS "file_status" (
  "name" varchar(255) NOT NULL COLLATE "default",
  "status" varchar(255) COLLATE "default",
  "url" varchar(255) COLLATE "default",
  CONSTRAINT pk_file_status PRIMARY KEY ("name")
)
WITH (OIDS=FALSE);

-- ----------------------------
--  Table structure for job
-- ----------------------------
CREATE TABLE IF NOT EXISTS "job" (
  "job_id" varchar(255) NOT NULL COLLATE "default",
  "expirytime" timestamp(6) NULL,
  "status" varchar(255) COLLATE "default",
  CONSTRAINT pk_job PRIMARY KEY ("job_id")
)
WITH (OIDS=FALSE);

-- ----------------------------
--  Table structure for job_file_status
-- ----------------------------
CREATE TABLE IF NOT EXISTS "job_file_status" (
  "job_job_id" varchar(255) NOT NULL COLLATE "default",
  "files_name" varchar(255) NOT NULL COLLATE "default",
  CONSTRAINT "pk_job_file_status" PRIMARY KEY ("job_job_id", "files_name"),
  CONSTRAINT "fk_job_file_status_files_name" FOREIGN KEY ("files_name") REFERENCES "file_status" ("name"),
  CONSTRAINT "fk_job_file_status_job_job_id" FOREIGN KEY ("job_job_id") REFERENCES "job" ("job_id")
)
WITH (OIDS=FALSE);

