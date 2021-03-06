/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.overlord.apiman.dt.api.beans.summary;

import java.io.Serializable;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * A bean modeling a summary of an Organization.  Typically used when listing
 * all Organizations visible to a user.
 *
 * @author eric.wittmann@redhat.com
 */
@Portable
public class OrganizationSummaryBean implements Serializable {

    private static final long serialVersionUID = -7969484509928874072L;
    
    private String id;
    private String name;
    private String description;
    private int numApps;
    private int numServices;
    private int numMembers;
    
    /**
     * Constructor.
     */
    public OrganizationSummaryBean() {
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the numApps
     */
    public int getNumApps() {
        return numApps;
    }

    /**
     * @param numApps the numApps to set
     */
    public void setNumApps(int numApps) {
        this.numApps = numApps;
    }

    /**
     * @return the numServices
     */
    public int getNumServices() {
        return numServices;
    }

    /**
     * @param numServices the numServices to set
     */
    public void setNumServices(int numServices) {
        this.numServices = numServices;
    }

    /**
     * @return the numMembers
     */
    public int getNumMembers() {
        return numMembers;
    }

    /**
     * @param numMembers the numMembers to set
     */
    public void setNumMembers(int numMembers) {
        this.numMembers = numMembers;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrganizationSummaryBean other = (OrganizationSummaryBean) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
