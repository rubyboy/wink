/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *  
 *   http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *  
 *******************************************************************************/
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-456 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.07.20 at 10:55:05 AM IST 
//

package org.apache.wink.common.model.rss;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for "skipDays" element of <a
 * href="http://www.rssboard.org/rss-specification">RSS 2.0 Specification</a>.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;rssSkipDays&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;day&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; maxOccurs=&quot;7&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * <h5>&lt;skipDays&gt; sub-element of &lt;channel&gt;</h5>
 * <p>
 * &lt;skipDays&gt; is an optional sub-element of &lt;channel&gt;. skipDays is
 * an hint for aggregators telling them which days they can skip. This element
 * contains up to seven &lt;day> sub-elements whose value is Monday, Tuesday,
 * Wednesday, Thursday, Friday, Saturday or Sunday. Aggregators may not read the
 * channel during days listed in the &lt;skipDays> element.
 * </p>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rssSkipDays", propOrder = {"day"})
public class RssSkipDays {

    protected List<String> day;

    /**
     * Gets the value of the day property.
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the day property.
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getDays().add(newItem);
     * </pre>
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     */
    public List<String> getDays() {
        if (day == null) {
            day = new ArrayList<String>();
        }
        return this.day;
    }

}
