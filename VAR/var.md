 1. 菜品信息表 (Dish)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| dish_id | dishId | Integer | 主键 |
| name | name | String | 菜品名称 |
| description | description | Text | 菜品描述 |
| processing_technology | processingTechnology | String | 菜品处理工艺 |
| ingredient_list | ingredientList | Text | 配料表 |
| dish_trace_code | dishTraceCode | String | 溯源码 |
| packaging_material | packagingMaterial | String | 包装材质 |
| storage_Condition | storageCondition | Text | 存储条件 |
| production_date | productionDate | Date | 生产日期 |
| shelf_Life | shelfLife | int | 保质期（天） |
| production_address | productionAddress | String | 生产地址 |
| allergen_warning | allergenWarning | Text | 致敏原提示 |
| created_time | createTime | DateTime | 创建时间 |
| updated_time | updateTime | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |

2. 生产信息表 (Production)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| production_id | id | Integer | 主键 |
| trace_code | traceCode | String | 溯源码 |
| production_process | productionProcess | Text | 生产加工流程 |
| processing_officer | processingOfficer | String | 加工负责人 |
| product_code | productCode | String | 产品代号 |
| production_license | productionLicense | String | 生产许可证号 |
| implementation_standard | implementationStandard | String | 执行标准 |
| production_workshopHygiene | productionWorkshopHygiene |	Text | 生产车间卫生状况记录 |
| operator_health_certificate | operatorHealthCertificate | String | 操作人员健康证明编号 |
| equipment_cleaning_record |equipmentCleaningRecord | Text |生产设备清洁记录 |
| tool_cleaning_record | toolCleaningRecord | Text | 生产工具清洁记录 |
| test_results | testResults | Text | 检测结果 |
| created_time | createTime | DateTime | 创建时间 |
| update_time | updateTime | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |

 3. 仓储信息表 (Storage)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| storage_id | id | Integer | 主键 |
| trace_code | traceCode | String | 溯源码 |
| storage_warehouse | storageWarehouse | String | 贮存仓库名称 |
| entry_date | entryDate | Date | 入库日期 |
| light_level | lightLevel | Float | 光照强度 |
| temperature | temperature | Float | 温度(℃) |
| humidity | humidity | Float | 湿度(%) |
| pm25 | pm25 | Float | PM2.5值 |
| oxygen_content | oxygenContent | Float | 含氧量(%) |
| packaging_status | packagingStatus | Text | 包装情况描述 |
| created_time | createTime | DateTime | 创建时间 |
| update_time | updateTime | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |

 4. 运输信息表 (Transport)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| transport_id | id | Integer | 主键 |
| trace_code | traceCode | String | 溯源码 |
| transporter | transporter | String | 运输公司名称 |
| transport_officer | transportOfficer | String | 运输负责人 |
| origin | origin | String | 出发地 |
| destination | destination | String | 目的地 |
| transport_batch_number | transportBatchNumber | String | 运输批号 |
| transport_license | transportLicense | String | 运输许可证号 |
| transport_gps | transportGps | Text | GPS轨迹数据 |
| created_time | createTime | DateTime | 创建时间 |
| update_time | updateTime | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |

 5. 供应商信息表 (Supplier)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| supplier_id | id | Integer | 主键 |
| traceCode | traceCode | String | 溯源码 |
| rawMaterial_supplier | rawMaterialSupplier | String | 原料供应商名称 |
| material_officer | materialOfficer | String | 原料负责人 |
| productTest_certificate | productTestCertificate | String | 产品检测证明编号 |
| created_time | createTime | DateTime | 创建时间 |
| update_time | updateTime | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |

 6. 公司信息表 (Company) 

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| company_id | id | Integer | 主键 |
| company_name | companyName | String | 公司名称 |
| legal_representative | legalRepresentative | String | 法定代表人 |
| company_email | companyEmail | String | 登录邮箱 |
| USCI | USCI | String | 统一社会信用代码 |
| company_profile | companyProfile | Text | 公司简介 |
| business_license_photo | businessLicensePhoto | Blob | 营业执照 |
| registeration_organ | registerationOrgan | String | 登录机关 |
| address | address | String | 公司地址 |
| created_time | createTime | DateTime | 创建时间 |
| update_time | updateTime | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |
| company_status | accountStatu | int | 公司状态 |

7. 召回信息表 (Recall)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| recall_id | id | Integer | 主键 |
| trace_code | traceCode | String | 溯源码 |
| recall_record | recallRecord | Text | 召回记录 |
| recall_history | recallHistory | Text | 召回历史 |
| recall_reason | recallReason | Text | 召回原因 |
| recall_officer | recallOfficer | String | 负责人 |
| created_time | createTime | DateTime | 创建时间 |
| update_time | updateTime | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |

 8. 交接记录表 (Handover)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| handover_id | id | Integer | 主键 |
| trace_code | traceCode | String | 溯源码 |
| handover_time | handoverTime | DateTime | 交接时间 |
| confirmation_record | confirmationRecord | Text | 确认记录 |
| acceptance_result | acceptanceResult | Text | 验收结果 |
| created_time | createTime | DateTime | 创建时间 |
| update_time | updateTime | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |

9. 用户表 (User)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| user_id | id | Integer | 主键 |
| phone_number | phoneNumber | String | 手机号 |
| email | email | string | 邮箱（找回密码）|
| password | password | String | 密码(加密存储) |
| created_time | createTime | DateTime | 创建时间 |
| update_time | updateTime | DateTime | 更新时间 |
| ip_address | ipAddress | String | ip地址记录 |
| account_status | accountStatu | int | 帐号状态 |

9(2).微信用户(wxUser)

10. 商家表 (Merchant)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| merchant_id | id | Integer | 主键 |
| enterprise_name | enterpriseName | String | 企业名称 |
| unified_social_creditCode | unifiedSocialCreditCode | String | 统一社会信用代码 |
| business_term | businessTerm | String | 经营期限 |
| legal_representative_name | legalRepresentativeName | String | 法定代表人姓名 |
| legal_representative_idNumber | legalRepresentativeIdNumber | String | 法定代表人身份证号码 |
| legal_representative_idValidity | legalRepresentativeIdValidity | Date | 法定代表人身份证有效期限 |
| legal_representative_phone | legalRepresentativePhone | String | 法定代表人手机号码 |
| merchant_password | merchantPassword | String | 密码(加密存储) |
| registration_email | registrationEmail | String | 注册邮箱 |
| account_status | accountStatu | int | 帐号状态 |
| created_time | createTime | DateTime | 创建时间 |
| update_time | updateTime | DateTime | 更新时间 |
| ip_address | ipAddress | String | ip地址记录 |
| register_address | registerAddress | String | 注册地址 |
| hash | hash | String | 哈希值 |

 11. 员工表 (Employee)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| employee_id | id | Integer | 主键 |
| name | name | String | 员工姓名 |
| password | password | String | 密码(加密存储) |
| role | role | String | 角色(admin/user等) |
| department | department | String | 所属部门 |
| position | position | String | 职位 |
| healthCertificateNumber | healthCertificateNumber | String | 健康证明编号 |
| healthCheckDate | healthCheckDate | Date | 最近一次健康检查日期 |
| healthStatus | healthStatus | String | 健康状况描述（如合格、需复查等）|
| infectiousDiseaseInfo | infectiousDiseaseInfo | Text | 传染病相关信息（如有则记录具体情况，无则为空） |
| contactNumber | contactNumber | String | 联系电话 |
| email | email | String | 电子邮箱 |
| created_time | createTime | DateTime | 创建时间 |
| update_time | updateTime | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |
| accountStatus | accountStatu | int | 帐号状态 |

12. 审核表 (Audit)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| audit_id | id | Integer | 主键 |
| trace_code | traceCode | String | 溯源码 |
| ai_beta | aiBeta | String | ai审核置信度 |
| ai_pass | aiPass | String | ai审核结果 |
| ai_percent | aiPercent | String | ai问题判断 |
| ai_accept_reason | aiAcceptReason | String | ai通过理由 |
| ai_reject_reason | aiRejectReason | String | ai拒绝理由 |
| adminer_accout | adminerAccout | String | 审核员 |
| audit_result | auditResult | int | 审核结果 |
| audit_reason | auditReason | String | 审核原因 |
| created_time | createTime | DateTime | 创建时间 |
| update_time | updateTime | DateTime | 更新时间 |
| hash | hash | String | 哈希值 |

13. 管理表 (Adminer)

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| admin_id | id | Integer | 主键 |
| account | account | String | 管理员账户 |
| password | password | String | 密码(加密存储) |
| department | department | String | 所属部门 |
| phone_number | phone_number | String | 联系电话 |
| email | email | String | 电子邮箱 |
| created_time | createTime | DateTime | 创建时间 |
| update_time | updateTime | DateTime | 更新时间 |


14. 品牌（brand）

| 字段名 | 变量名 | 类型 | 描述 |
|--------|--------|------|------|
| brand_id | id | Integer | 主键 |
| brand_name | name | String | 品牌名称 |
| brand_description |brandDescription |String |品牌简介 |
| company | company | String | 公司（外联表） |


data:text/markdown;charset=utf-8