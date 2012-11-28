/**
 * 
 */
package com.lce.atg.cr2;

import java.util.ArrayList;

/**
 * @author rdunigan
 *
 */
public class ParentItem {
    private String mTitle;
    private ArrayList<String> mArrayChildren;
 
    public String getTitle() {
        return mTitle;
    }
 
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
 
    public ArrayList<String> getArrayChildren() {
        return mArrayChildren;
    }
 
    public void setArrayChildren(ArrayList<String> mArrayChildren) {
        this.mArrayChildren = mArrayChildren;
    }

}
