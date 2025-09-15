 1. 菜品信息表 (Dish)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| dish_id | id | Integer | 主键 |
| dish_name | name | String | 菜品名称 |
| dish_trace_code | traceCode | String | 溯源码 |
| packaging_Material | packagingMaterial | String | 包装材质 |
| ingredient_List | ingredientList | Text | 配料表 |
| production_Date | productionDate | Date | 生产日期 |
| storage_Condition | storageCondition | Text | 存储条件 |
| shelf_Life | shelfLife | int | 保质期(天） |
| allergen_Warning | allergenWarning | Text | 致敏原提示 |
| created_Time | createdAt | DateTime | 创建时间 |
| updated_time | updatedAt | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |

2. 生产信息表 (Production)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| id | id | Integer | 主键 |
| traceCode | traceCode | String | 溯源码 |
| productionProcess | productionProcess | Text | 生产加工流程 |
| processingOfficer | processingOfficer | String | 加工负责人 |
| productCode | productCode | String | 产品代号 |
| productionLicense | productionLicense | String | 生产许可证号 |
| implementationStandard | implementationStandard | String | 执行标准 |
| productionWorkshopHygiene | productionWorkshopHygiene |	Text | 生产车间卫生状况记录 |
| operatorHealthCertificate | operatorHealthCertificate | String | 操作人员健康证明编号 |
| equipmentCleaningRecord |equipmentCleaningRecord | Text |生产设备清洁记录 |
| toolCleaningRecord | toolCleaningRecord | Text | 生产工具清洁记录 |
| testResults | testResults | Text | 检测结果 |
| createdAt | createdAt | DateTime | 创建时间 |
| updatedAt | updatedAt | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |

 3. 仓储信息表 (Storage)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| id | id | Integer | 主键 |
| traceCode | traceCode | String | 溯源码 |
| storageWarehouse | storageWarehouse | String | 贮存仓库名称 |
| entryDate | entryDate | Date | 入库日期 |
| lightLevel | lightLevel | Float | 光照强度 |
| temperature | temperature | Float | 温度(℃) |
| humidity | humidity | Float | 湿度(%) |
| pm25 | pm25 | Float | PM2.5值 |
| oxygenContent | oxygenContent | Float | 含氧量(%) |
| packagingStatus | packagingStatus | Text | 包装情况描述 |
| createdAt | createdAt | DateTime | 创建时间 |
| updatedAt | updatedAt | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |

 4. 运输信息表 (Transport)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| id | id | Integer | 主键 |
| traceCode | traceCode | String | 溯源码 |
| transporter | transporter | String | 运输公司名称 |
| transportOfficer | transportOfficer | String | 运输负责人 |
| origin | origin | String | 出发地 |
| destination | destination | String | 目的地 |
| transportBatchNumber | transportBatchNumber | String | 运输批号 |
| transportLicense | transportLicense | String | 运输许可证号 |
| transportGps | transportGps | Text | GPS轨迹数据 |
| createdAt | createdAt | DateTime | 创建时间 |
| updatedAt | updatedAt | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |

 5. 供应商信息表 (Supplier)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| id | id | Integer | 主键 |
| traceCode | traceCode | String | 溯源码 |
| rawMaterialSupplier | rawMaterialSupplier | String | 原料供应商名称 |
| materialOfficer | materialOfficer | String | 原料负责人 |
| productTestCertificate | productTestCertificate | String | 产品检测证明编号 |
| createdAt | createdAt | DateTime | 创建时间 |
| updatedAt | updatedAt | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |

 6. 公司信息表 (Company)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| id | id | Integer | 主键 |
| companyName | companyName | String | 公司名称 |
| responsiblePerson | responsiblePerson | String | 负责人实名 |
| businessLicense | businessLicense | String | 营业执照号 |
| companyEmail | companyEmail | String | 公司邮箱 |
| socialCertificationNumber | socialCertificationNumber | String | 社会认证号 |
| companyProfile | companyProfile | Text | 公司简介 |
| companyPhoto | companyPhoto | Blob | 公司照片 |
| createdAt | createdAt | DateTime | 创建时间 |
| updatedAt | updatedAt | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |
| companyStatus | accountStatu | int | 公司状态 |

7. 召回信息表 (Recall)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| id | id | Integer | 主键 |
| traceCode | traceCode | String | 溯源码 |
| recallRecord | recallRecord | Text | 召回记录 |
| recallHistory | recallHistory | Text | 召回历史 |
| recallReason | recallReason | Text | 召回原因 |
| recallOfficer | recallOfficer | String | 负责人 |
| createdAt | createdAt | DateTime | 创建时间 |
| updatedAt | updatedAt | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |

 8. 交接记录表 (Handover)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| id | id | Integer | 主键 |
| traceCode | traceCode | String | 溯源码 |
| handoverTime | handoverTime | DateTime | 交接时间 |
| confirmationRecord | confirmationRecord | Text | 确认记录 |
| acceptanceResult | acceptanceResult | Text | 验收结果 |
| createdAt | createdAt | DateTime | 创建时间 |
| updatedAt | updatedAt | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |

9. 用户表 (User)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| id | id | Integer | 主键 |
| phoneNumber | phoneNumber | String | 手机号 |
| userEmail | userEmail | string | 邮箱（找回密码）|
| password | password | String | 密码(加密存储) |
| verificationCode | verificationCode | String | 验证码 |
| createdAt | createdAt | DateTime | 创建时间 |
| updatedAt | updatedAt | DateTime | 更新时间 |
| ipAddress | ipAddress | String | ip地址记录 |
| accountStatus | accountStatu | int | 帐号状态 |

9(2).微信用户(wxUser)

10. 商家表 (Merchant)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| id | id | Integer | 主键 |
| enterpriseName | enterpriseName | String | 企业名称 |
| unifiedSocialCreditCode | unifiedSocialCreditCode | String | 统一社会信用代码 |
| businessTerm | businessTerm | String | 经营期限 |
| legalRepresentativeName | legalRepresentativeName | String | 法定代表人姓名 |
| legalRepresentativeIdNumber | legalRepresentativeIdNumber | String | 法定代表人身份证号码 |
| legalRepresentativeIdValidity | legalRepresentativeIdValidity | Date | 法定代表人身份证有效期限 |
| legalRepresentativePhone | legalRepresentativePhone | String | 法定代表人手机号码 |
| merchantPassword | merchantPassword | String | 密码(加密存储) |
| registrationEmail | registrationEmail | String | 注册邮箱 |
| passwordHint | passwordHint | String | 密码提示词 |
| passwordHintAnswer | passwordHintAnswer | String | 密码提示答案 |
| merchantVerificationCode | merchantVerificationCode | String | 验证码 |
| accountStatus | accountStatu | int | 帐号状态 |
| createdAt | createdAt | DateTime | 创建时间 |
| updatedAt | updatedAt | DateTime | 更新时间 |
| ipAddress | ipAddress | String | ip地址记录 |
| registerAddress | registerAddress | String | 注册地址 |
| hash | hash | String | 哈希值 |

 11. 员工表 (Employee)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| id | id | Integer | 主键 |
| name | name | String | 员工姓名 |
| password | password | String | 密码(加密存储) |
| role | role | String | 角色(admin/user等) |
| department | department | String | 所属部门 |
| position | position | String | 职位 |
|healthCertificateNumber | healthCertificateNumber | String | 健康证明编号 |
|healthCheckDate | healthCheckDate | Date | 最近一次健康检查日期 |
|healthStatus | healthStatus | String | 健康状况描述（如合格、需复查等）|
|infectiousDiseaseInfo | infectiousDiseaseInfo | Text | 传染病相关信息（如有则记录具体情况，无则为空） |
| contactNumber | contactNumber | String | 联系电话 |
| email | email | String | 电子邮箱 |
| createdAt | createdAt | DateTime | 创建时间 |
| updatedAt | updatedAt | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |
| accountStatus | accountStatu | int | 帐号状态 |

12. 审核表 (Audit)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| traceCode | traceCode | String | 溯源码 |
| aiBeta | aiBeta | String | ai审核置信度 |
| aiPass | aiPass | String | ai审核结果 |
| aiPercent | aiPercent | String | ai问题判断 |
| aiAcceptReason | aiAcceptReason | String | ai通过理由 |
| aiRejectReason | aiRejectReason | String | ai拒绝理由 |
| adminerAccout | adminerAccout | String | 审核员 |
| auditResult | auditResult | int | 审核结果 |
| auditReason | auditReason | String | 审核原因 |
| createdAt | createdAt | DateTime | 创建时间 |
| updatedAt | updatedAt | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |

13. 管理表 (Adminer)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| id | id | Integer | 主键 |
| accoutNumber | accoutNumbe | String | 管理员账户 |
| password | password | String | 密码(加密存储) |
| department | department | String | 所属部门 |
| contactNumber | contactNumber | String | 联系电话 |
| email | email | String | 电子邮箱 |
| createdAt | createdAt | DateTime | 创建时间 |
| updatedAt | updatedAt | DateTime | 更新时间 |


data:text/markdown;charset=utf-8