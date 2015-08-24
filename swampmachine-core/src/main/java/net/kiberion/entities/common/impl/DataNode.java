/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.entities.common.impl;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.entities.common.api.TaggedEntity;

/**
 * @author kibertoad
 */
public class DataNode extends EntityModel implements TaggedEntity {

	@Setter
    public String group;
	
	@Setter
	@Getter
    public String subGroup;
    public Set<String> tags = new HashSet<>();

    public String groupID;
    public String subGroupID;
    
    public int rating = -1;

    @Getter
    @Setter
    private String description; 

	

    public DataNode() {
    }

    public DataNode(String setName, String setCode) {
        setName (setName);
        setId(setCode);
    }

    @Override
    public boolean hasTag(String theTag) {
        return tags.contains(theTag);
    }

    public void addTag(String tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    @Override
    public Set<String> getTags() {
        return tags;
    }
    

    @Override
    public String toString() {
        return getMetadata().getName();
    }

    @Override
    public String getGroup() {
        return group;
    }

    public int getRating() {
        return rating;
    }

    public void outputTags() {
        for (String s : tags) {
            Gdx.app.log("debug", "Tag: " + s);
        }
    }

    @Override
    public String getName() {
        if (getMetadata().getName() != null) {
            return getMetadata().getName();
        } else {
            return super.getName();
        }
    }

    @Override
    public void setName(String name) {
        this.getMetadata().setName(name);
    }

    public void setImage(String imageCode) {

    }
}