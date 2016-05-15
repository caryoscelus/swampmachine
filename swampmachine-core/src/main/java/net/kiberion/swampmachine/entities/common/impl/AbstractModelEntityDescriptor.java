package net.kiberion.swampmachine.entities.common.impl;

import net.kiberion.swampmachine.entities.common.api.EntityModelDescriptor;
import net.kiberion.swampmachine.entityblocks.impl.CommonMetadataHolderAspect;


/**
 * @author kibertoad
 */
public abstract class AbstractModelEntityDescriptor extends CommonMetadataHolderAspect implements EntityModelDescriptor {

    /*
     * E. g. for equipment slot return both slot and item name
     */
    @Override
    public String getExtendedName() {
        return this.getMetadata().getId();
    }

    @Override
    public String getName() {
        return this.getMetadata().getName();
    }

    public void setName(String name) {
        this.getMetadata().setName(name);
    }

    @Override
    public String getGroup() {
        return this.getMetadata().getGroup();
    }

    @Override
    public void setGroup(String toGroup) {
    	throw new UnsupportedOperationException("Unsupported operation");
    }

    public void setSubGroup(String toGroup) {
    	throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public String getId() {
        return getMetadata().getId();
    }

    @Override
    public void setId(String id) {
        getMetadata().setId(id);
    }

    @Override
    public String toString() {
        return this.getMetadata().toString();
    }
}
