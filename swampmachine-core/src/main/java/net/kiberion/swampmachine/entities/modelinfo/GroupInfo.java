/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.swampmachine.entities.modelinfo;

import java.util.HashMap;
import java.util.Map;

import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

/**
 *
 * @author kibertoad
 */
public class GroupInfo extends CommonModelEntityDescriptor {

    public Map<String, GroupInfo> subGroups = new HashMap<>();
   // public TextureRegion image;

    public ActionInfo defaultAction;
    public String defaultActionCode; //used to lazily load actions

}