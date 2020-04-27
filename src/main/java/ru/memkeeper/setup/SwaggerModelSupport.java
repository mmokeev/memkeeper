package ru.memkeeper.setup;

import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.Optional;

import static springfox.documentation.schema.Annotations.findPropertyAnnotation;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1000)
public class SwaggerModelSupport implements ModelPropertyBuilderPlugin {
    @Override
    public void apply(ModelPropertyContext context) {
        Optional.ofNullable(context.getBeanPropertyDefinition().orNull()).ifPresent(beanPropertyDefinition -> {
            AnnotatedMethod accessorMethod = (AnnotatedMethod) beanPropertyDefinition.getAccessor();

            context.getBuilder().required(isRequiredAttribute(beanPropertyDefinition) || isRequiredType(accessorMethod));
        });
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }

    private boolean isRequiredAttribute(BeanPropertyDefinition beanPropertyDefinition) {
        ApiModelProperty annotation = findPropertyAnnotation(beanPropertyDefinition, ApiModelProperty.class).orNull();
        if (annotation != null) {
            return annotation.required();
        }
        return false;
    }

    private boolean isRequiredType(AnnotatedMethod annotatedMethod) {
        boolean isOptional = annotatedMethod.getRawType().equals(Optional.class);
        boolean isDefaultMethod = annotatedMethod.getMember().isDefault();
        boolean isCollection = annotatedMethod.getType().isCollectionLikeType();

        return !(isOptional || isDefaultMethod || isCollection);
    }
}
