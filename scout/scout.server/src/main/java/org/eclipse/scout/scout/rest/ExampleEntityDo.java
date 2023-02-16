package org.eclipse.scout.scout.rest;

import org.eclipse.scout.rt.dataobject.DoEntity;
import org.eclipse.scout.rt.dataobject.DoValue;
import org.eclipse.scout.rt.dataobject.TypeName;
import org.eclipse.scout.rt.dataobject.TypeVersion;
import org.eclipse.scout.scout.server.ScoutTypeVerions;

import javax.annotation.Generated;

@TypeVersion(ScoutTypeVerions.Scout23.class)
@TypeName("server.ExampleEntity")
public class ExampleEntityDo extends DoEntity {

    public DoValue<String> test() {
        return doValue("test");
    }

    public DoValue<String> code() {
        return doValue("code");
    }

    /* **************************************************************************
     * GENERATED CONVENIENCE METHODS
     * *************************************************************************/

    @Generated("DoConvenienceMethodsGenerator")
    public ExampleEntityDo withTest(String test) {
        test().set(test);
        return this;
    }

    @Generated("DoConvenienceMethodsGenerator")
    public String getTest() {
        return test().get();
    }

    @Generated("DoConvenienceMethodsGenerator")
    public ExampleEntityDo withCode(String code) {
        code().set(code);
        return this;
    }

    @Generated("DoConvenienceMethodsGenerator")
    public String getCode() {
        return code().get();
    }
}
