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
| available | 赛事是否通过 | 用于赛事的审核 | int(1) | notNull |
| status | 赛事的状态 | 说明赛事的状态 | int(1) | -1未开始 0报名中 1已开始 2已结束、notNull |
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
| eventId | 赛事id | 无 | int(10) | notNull |
| adminUid | 用户id | 无 | int(10) | notNull |
### player表 （赛事与选手对应表）
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| eventId | 赛事id | 无 | int(10) | notNull |
| playerUid | 选手uid | 无 | int(10) | notNull |
| isPassed | 是否通过审核 | 是否通过赛事管理员审核 | int(1) | notNull |
| isPayed | 是否缴费 | 无 | int(1) | notNull |
| weight | 权重 | 用于判断选手的排位 | int(10) | notNull，默认0 |
| identifyMsg | 验证信息 | 选手报名时验证的信息 | varchar(255) | 无 |
| submitDate | 提交时间 | 选手提交报名信息的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、默认当前时间 |
### referee表 (赛事与餐盘对应表)
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| eventId | 赛事id | 无 | int(10) | notNull |
| refereeUid | 裁判uid | 无 | int(10) | notNull |
| isPassed | 是否通过审核 | 是否通过赛事管理员审核 | int(1) | notNull |
| identifyMsg | 验证信息 | 裁判报名时验证的信息 | varchar(255) | 无 |
| submitDate | 提交时间 | 裁判提交报名信息的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、默认当前时间 |
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

