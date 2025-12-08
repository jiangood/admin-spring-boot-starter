package io.admin.framework.data.id.impl;

import io.admin.common.utils.IdUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * 按时间排序的uuid
 * 使用了uuidv7
 */
public class UuidV7IdGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return IdUtils.uuidV7();
    }
}
