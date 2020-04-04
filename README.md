# SportMatch
赛事系统
---
## 赛事阶段数据库设计
---
### user表 （用户表）
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| uid | 用户的id | 无 | int(10) | 主键自增 |
| username | 用户名 | 无 | varchar(255) | notnull |
| password | 密码 | 无 | varchar(255) | notnull |
| nickname | 昵称 | 无 | carchar(255) | notnull |
| createDate | 创建时间 | 无 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、创建时默认当前时间 |
### user_detail (用户详细信息表)
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| uid | 用户的id | 无 | int(10) | 主键自增 |
| realname | 真实姓名 | 无 | varchar(255) | notnull |
| phoneNumber | 手机号码 | 无 | int(8) | notnull |
| identityNumber | 身份证号 | 无 | varchar(255)) | notnull |
| age | 年龄 | 无 | int(8) | notnull |
| school | 学校 | 无 | varchar(255) | 无 |
| studentId | 学号 | 无 | varchar(255) | 无 | 
| location | 地址 | 无 | varchar(255) | notnull |
| height | 身高 | 无 | varchar(255) | notnull |
| weight | 体重 | 无 | varchar(255) | notnull |
| sex | 性别 | 无 | varchar(255) | notnull |
### event表 （赛事的基本信息）
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| eventId | 赛事的id | 标识每一个赛事 | int(10) | 主键自增、notNull |
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
### event_cptItem表 （赛事的比赛项目）
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| eventId | 赛事id | 无 | int(10) | 无 |
| itemId | 项目id | 无 | int(8) | 无 |
| unit | 单位 | 每个队伍人数的多少 | int(1) | notnull、1个人、1以上团体 |
| itemName | 项目名称 | 无 | int（8）| 无 |
| ageLimit | 年龄限制 | 无 | int(8) | 无 |
| sexLimit | 性别限制 | 无 | varchar(255) | 无 |
| createDate | 创建时间 | 创建的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、创建时默认当前时间 |
### event_cptItem_player表 （赛事比赛项目与选手对应表）
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| eventId | 赛事id | 无 | int(10) | 无 |
| itemId | 项目id | 无 | int(8) | 无 |
| playerUid | 选手uid | 无 | int(10) | 无|
| isPassed | 是否通过审核 | 是否通过赛事管理员审核 | int(1) | notNull |
| groupNum | 小组赛组号 | ABCDEF | varchar(1) | notNull,不是小组赛为0 |
| integral | 积分 | 用于判断选手的排位 | int(10) | notNull，默认0 |
| createDate | 创建时间 | 创建的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、创建时默认当前时间 |
### event_cptItem_team表 （赛事比赛项目与队伍对应表）
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| eventId | 赛事id | 无 | int(10) | 无 |
| itemId | 项目id | 无 | int(8) | 无 |
| teamId | 团队id | 无 | int(10) | 无|
| isPassed | 是否通过审核 | 是否通过赛事管理员审核 | int(1) | notNull |
| groupNum | 小组赛组号 | ABCDEF | varchar(1) | notNull,不是小组赛为0 |
| integral | 积分 | 用于判断队伍的排位 | int(10) | notNull，默认0 |
| createDate | 创建时间 | 创建的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、创建时默认当前时间 |
### team (队伍表)
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| teamId | 队伍id | 无 | int(8) | 主键,notnull |
| teamName | 队伍昵称 | 无 | vachar(255) | notnull |
| creatorUid | 创建者uid | 无 | int(10) | notnull |
| createDate | 创建时间 | 创建的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、创建时默认当前时间 |
### team_player （队伍选手对应表）
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| teamId | 团队id | 无 | int(10) | notnull |
| playerUid | 选手uid | 无 | int(10) | notNull |
| role | 角色 | 队伍中的角色 | int(1) | notnull,1队长0成员，队长有操作权限 |
| createDate | 创建时间 | 创建的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、创建时默认当前时间 |
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
| number | 选手的号码 | 无 | int(2) | 无 |
| isPassed | 是否通过审核 | 是否通过赛事管理员审核 | int(1) | notNull |
| isPayed | 是否缴费 | 无 | int(1) | notNull |
| identifyMsg | 验证信息 | 选手报名时验证的信息 | varchar(255) | 无 |
| submitDate | 最后提交时间 | 选手最近一次提交报名信息的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、更新时默认当前时间 |
| createDate | 创建时间 | 选手第一次提交的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、创建时默认当前时间 |
### team表 （赛事与队伍对应表）
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| eventId | 赛事id | 无 | int(10) | notNull,主键|
| teamId | 队伍id | 无 | int(10) | notNull，主键|
| number | 队伍的号码 | 无 | int(2) | 无 |
| isPassed | 是否通过审核 | 是否通过赛事管理员审核 | int(1) | notNull |
| isPayed | 是否缴费 | 无 | int(1) | notNull |
| identifyMsg | 验证信息 | 队伍报名时验证的信息 | varchar(255) | 无 |
| submitDate | 最后提交时间 | 队伍最近一次提交报名信息的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、更新时默认当前时间 |
| createDate | 创建时间 | 队伍第一次提交的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、创建时默认当前时间 |
### referee表 (赛事与裁判对应表)
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| eventId | 赛事id | 无 | int(10) | notNull，主键 |
| refereeUid | 裁判uid | 无 | int(10) | notNull，主键 |
| isPassed | 是否通过审核 | 是否通过赛事管理员审核 | int(1) | -1未审核、0未通过、1已通过、notNull |
| identifyMsg | 验证信息 | 裁判报名时验证的信息 | varchar(255) | 无 |
| submitDate | 最后提交时间 | 裁判最近一次提交报名信息的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、更新时默认当前时间 |
| createDate | 创建时间 | 裁判第一次提交的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、创建时默认当前时间 |
### baseball_single_schedule表 (网球赛事单打场次表)
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| eventId | 赛事id | 无 | int(10) | 无 |
| itemId | 项目id | 无 | int(8) | 无 |
| roundId | 场次id | 无 | int(10) | notNull,主键 |
| roundNum | 赛事的第几轮| 无 | int(10) | notNull |
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
| winnnerId | 获胜者的id| 无 | int(10) | 无 |
| isRecord | 是否记录 | 比赛结束后数据库是否已记录选手比赛中的权重 | int(1) | notnull，默认0，记录为1 |
| createDate | 创建时间 | 创建的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、创建时默认当前时间 |
### baseball_double_schedule表 (网球赛事双打场次表)
| 字段 | 字段的名称 | 描述 | 字段的类型 | 备注 |
| --- | --- | --- | --- | ---- |
| eventId | 赛事id | 无 | int(10) | 无 |
| itemId | 项目id | 无 | int(8) | 无 |
| roundId | 场次id | 无 | int(10) | notNull,主键 |
| roundNum | 赛事的第几轮| 无 | int(10) | notNull |
| startDate | 场次开始时间 | 无 | timestamp | yyyy-MM-dd HH:mm:ss、notNull |
| teamAId | 队伍A的uid | 无 | int(10) | notNull |
| teamBId | 队伍B的uid | 无 | int(10) | notNull |
| refereeUid | 裁判uid | 无 | int(10) | notNull |
| aWinRound | 队伍A赢的局数 | 无 | int(2) | notNull,默认为0 |
| bWinRound | 队伍B赢的局数 | 无 | int(2) | notNull，默认为0 |
| aCurrentRoundScore | 队伍A当前轮的得分 | 无 | int(2） | notNull,默认为0 |
| bCurrentRoundScore | 队伍B当前轮的得分 | 无 | int(2） | notNull,默认为0 |
| countNum | 比赛场次场地序号 | 无 | int(8) | notNull |
| isStart | 比赛场次是否开始 | 无 | int(1) | notNull，默认为0 |
| isFinish | 比赛场次是否结束 | 无 | int(1) | notNUll，默认为0 |
| winnnerId | 获胜者的id | 无 | int(10) | 无 |
| isRecord | 是否记录 | 比赛结束后数据库是否已记录选手比赛中的权重 | int(1) | notnull，默认0，记录为1 |
| createDate | 创建时间 | 创建的时间 | timestamp | yyyy-MM-dd HH:mm:ss、notNull、创建时默认当前时间 |


