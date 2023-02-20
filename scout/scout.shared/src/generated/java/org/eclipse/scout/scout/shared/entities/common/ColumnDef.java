package org.eclipse.scout.scout.shared.entities.common;

import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ColumnDef {


  @ClassId("d3bb216d-00fc-4d69-b7ab-f423e477f860")
  static final class NullCodeType extends AbstractCodeType<Long, Long> {
    private static final long serialVersionUID = 1L;
    @Override
    public Long getId() {
      return null;
    }
  }

  String name();

  SqlDataType type();

  Class<? extends ICodeType<Long, Long>> codeType() default NullCodeType.class;

}
