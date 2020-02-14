# SportMatch
赛事系统
---
## 赛事阶段数据库设计
---
### event表 （赛事的基本信息）
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| id | 赛事的id | 标识每一个赛事 | int(10) | 主键自增、notNull |
| type | 赛事的种类 | 说明赛事的类型 | int(10) | 比如：1网球2篮球、notNull |
| available | 赛事是否通过 | 用于赛事的审核 | int(1) | -1提交待审核 0未通过审核 1通过审核、notNull |
| status | 赛事的状态 | 说明赛事的状态 | int(1) | -1未开始报名 0开始报名中 1报名结束未开始比赛 2已开始比赛 3比赛已结束、notNull |
| creatorUid | 创建者uid | 无 | int(10) | notNull |
| identifyType | 验证类型 | 用于赛事对参赛人员的验证 | int(1) | 0不需要验证 1需要密码 2需要人工审核 、 notNull |
| password | 验证密码 | 用于输入验证密码 | varchar(255) | 无 |
| createDate | 创建时间 | 无 | timestamp | yyyy-MM-dd HH:mm:ss、notNull |
| signUpBeginDate | 报名开始时间 | 无 | timestamp | 同上 |
| signUpEndDate | 报名结束时间 | 无 | timestamp | 同上 |
| startDate | 比赛开始时间 | 无 | timestamp | 同上 |
| name | 赛事名字 | 无 | varchar(255) | notNull |
| rule | 赛事规则 | 无 | varchar(255) | notNull |
| expenses | 参赛费用 | 无 | float(255,0) | notNull |
| maxPlayer | 最大参赛人数 | 无 | int(10) | notNull |
| hostName | 主办方 | 无 | varchar(255) | notNull |
| hostAddress | 主办地 | 比赛的位置 | varchar(255) | notNull |
| bonusRule | 奖励规则 | 比赛的奖励规则 | varchar(255) | 无 |
| eligibility | 参赛资格 | 满足要求即能参加比赛 | varchar(255) | 无 |
| remark | 备注 | 用于填写以上未列出的项目 | varchar(255) | 无 |
### event_admin表 （赛事与赛事管理员对应表）
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| eventId | 赛事id | 无 | int(10) | notNull,主键 |
| adminUid | 用户id | 无 | int(10) | notNull,主键 |
| submitDate | 最后提交时间 | 赛事管理员最近一次提交报名信息的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、更新时默认当前时间 |
| createDate | 创建时间 | 赛事管理员第一次提交的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、创建时默认当前时间 |
### player表 （赛事与选手对应表）
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| eventId | 赛事id | 无 | int(10) | notNull,主键|
| playerUid | 选手uid | 无 | int(10) | notNull，主键|
| isPassed | 是否通过审核 | 是否通过赛事管理员审核 | int(1) | notNull |
| isPayed | 是否缴费 | 无 | int(1) | notNull |
| weight | 权重 | 用于判断选手的排位 | int(10) | notNull，默认0 |
| identifyMsg | 验证信息 | 选手报名时验证的信息 | varchar(255) | 无 |
| submitDate | 最后提交时间 | 选手最近一次提交报名信息的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、更新时默认当前时间 |
| createDate | 创建时间 | 选手第一次提交的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、创建时默认当前时间 |
### referee表 (赛事与餐盘对应表)
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| eventId | 赛事id | 无 | int(10) | notNull，主键 |
| refereeUid | 裁判uid | 无 | int(10) | notNull，主键 |
| isPassed | 是否通过审核 | 是否通过赛事管理员审核 | int(1) | -1未审核、0未通过、1已通过、notNull |
| identifyMsg | 验证信息 | 裁判报名时验证的信息 | varchar(255) | 无 |
| submitDate | 最后提交时间 | 裁判最近一次提交报名信息的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、更新时默认当前时间 |
| createDate | 创建时间 | 裁判第一次提交的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、创建时默认当前时间 |
### baseball_schedule表 (网球赛事场次表)
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| eventId | 赛事id | 无 | int(10) | notNull |
| startDate | 场次开始时间 | 无 | timestamp | yyyy-MM-dd HH:mm:ss、notNull |
| playerAUid | 选手A的uid | 无 | int(10) | notNull |
| playerBUid | 选手B的uid | 无 | int(10) | notNull |
| refereeUid | 裁判uid | 无 | int(10) | notNull |
| aWinRound | 选手A赢的局数 | 无 | int(2) | notNull,默认为0 |
| bWinRound | 选手B赢的局数 | 无 | int(2) | notNull，默认为0 |
| aCurrentRoundScore | 选手A当前轮的得分 | 无 | int(2） | notNull,默认为0 |
| bCurrentRoundScore | 选手B当前轮的得分 | 无 | int(2） | notNull,默认为0 |
| countNum | 比赛场次场地序号 | 无 | int(8) | notNull |
| isStart | 比赛场次是否开始 | 无 | int(1) | notNull，默认为0 |
| isFinish | 比赛场次是否结束 | 无 | int(1) | notNUll，默认为0 |
| winnnerUid | 获胜者的id | 无 | int(10) | 无 |
| createDate | 创建时间 | 创建的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、创建时默认当前时间 |

