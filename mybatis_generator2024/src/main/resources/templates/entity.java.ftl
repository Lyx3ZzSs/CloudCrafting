package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if springdoc>
import io.swagger.v3.oas.annotations.media.Schema;
<#elseif swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
<#if chainModel>
    import lombok.experimental.Accessors;
</#if>
</#if>

/**
* <p>
* ${table.comment!}
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if entityLombokModel>
@Data
@AllArgsConstructor
@NoArgsConstructor
<#if chainModel>
    @Accessors(chain = true)
</#if>
</#if>
<#if table.convert>
@TableName("${schemaName}${table.name}")
</#if>
<#if springdoc>
@Schema(name = "${entity}", description = "${table.comment!}")
<#elseif swagger>
@ApiModel(value = "${entity}对象", description = "${table.comment!}")
</#if>
<#if superEntityClass??>
public class ${entity} extends ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
public class ${entity} extends Model<${entity}> {
<#elseif entitySerialVersionUID>
public class ${entity} implements Serializable {
<#else>
public class ${entity} {
</#if>

<#if entitySerialVersionUID>
    private static final long serialVersionUID = 1L;
</#if>

<#-- 字段循环遍历 -->
<#list table.fields as field>
    <#if field.comment!?length gt 0>
    <#if springdoc>
    @Schema(description = "${field.comment}")
    <#elseif swagger>
    @ApiModelProperty("${field.comment}")
    </#if>
    </#if>
    <#if field.keyFlag>
    @TableId(value = "${field.annotationColumnName}", type = <#if field.keyIdentityFlag>IdType.AUTO<#else>IdType.${idType}</#if>)
    <#else>
    <#if field.fill??>
    @TableField(value = "${field.annotationColumnName}", fill = FieldFill.${field.fill})
    <#else>
    @TableField("${field.annotationColumnName}")
    </#if>
    </#if>
    <#if field.versionField>
    @Version
    </#if>
    <#if field.logicDeleteField>
    @TableLogic
    </#if>
    private ${field.propertyType} ${field.propertyName};

</#list>
<#if !entityLombokModel>
    <#list table.fields as field>
    <#if field.propertyType == "boolean">
        <#assign getprefix="is"/>
    <#else>
        <#assign getprefix="get"/>
    </#if>

    public ${field.propertyType} ${getprefix}${field.capitalName}() {
    return ${field.propertyName};
    }

    public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
    this.${field.propertyName} = ${field.propertyName};
    }
    </#list>
</#if>
<#if entityColumnConstant>
    <#list table.fields as field>
    public static final String ${field.name?upper_case} = "${field.name}";
    </#list>
</#if>
<#if activeRecord>
    @Override
    public Serializable pkVal() {
    <#if keyPropertyName??>
        return this.${keyPropertyName};
    <#else>
        return null;
    </#if>
    }
</#if>
<#if !entityLombokModel>
    @Override
    public String toString() {
    return "${entity}{" +
    <#list table.fields as field>
        <#if field_index == 0>
            "${field.propertyName}=" + ${field.propertyName} +
        <#else>
            ", ${field.propertyName}=" + ${field.propertyName} +
        </#if>
    </#list>
    "}";
    }
</#if>
}