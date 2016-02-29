/* 
 * AbstractVersionInfo.java
 *
 * Copyright (c) 2009–2014 Steinbeis Forschungszentrum (STZ Ölbronn),
 * Copyright (c) 2006–2014 by Michael Hoffer
 * 
 * This file is part of Visual Reflection Library (VRL).
 *
 * VRL is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * see: http://opensource.org/licenses/LGPL-3.0
 *      file://path/to/VRL/src/eu/mihosoft/vrl/resources/license/lgplv3.txt
 *
 * VRL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * This version of VRL includes copyright notice and attribution requirements.
 * According to the LGPL this information must be displayed even if you modify
 * the source code of VRL. Neither the VRL Canvas attribution icon nor any
 * copyright statement/attribution may be removed.
 *
 * Attribution Requirements:
 *
 * If you create derived work you must do three things regarding copyright
 * notice and author attribution.
 *
 * First, the following text must be displayed on the Canvas or an equivalent location:
 * "based on VRL source code".
 * 
 * Second, the copyright notice must remain. It must be reproduced in any
 * program that uses VRL.
 *
 * Third, add an additional notice, stating that you modified VRL. In addition
 * you must cite the publications listed below. A suitable notice might read
 * "VRL source code modified by YourName 2012".
 * 
 * Note, that these requirements are in full accordance with the LGPL v3
 * (see 7. Additional Terms, b).
 *
 * Publications:
 *
 * M. Hoffer, C.Poliwoda, G.Wittum. Visual Reflection Library -
 * A Framework for Declarative GUI Programming on the Java Platform.
 * Computing and Visualization in Science, in press.
 */

package eu.mihosoft.vrl.vupdater.core;

/**
 * Defines a VRL version information. This class specifies how to compare
 * versions. This is used to check file format compatibility or plugin version
 * etc.
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
interface AbstractVersionInfo {

    /**
     * Compares file versions.
     * <p>
     * Versions are compared as follows:
     * </p>
     * The number of elements to compare is defined by the minimum version array
     * size. Iterate through all elements to compare. If elements differ then
     * break and return
     * </p>
     * <p>
     * <ul>
     * <li><code>-1</code> if the first element is smaller then the second
     * element</li>
     * <li><code>1</code> if the first element is greater then the second
     * element</li>
     * </ul>
     * </p>
     * @param o the version to compare to
     * @return <code>-1</code> if this version info is smaller,
     * <code>1</code> if this version info is bigger,
     * <code>0</code> if both version infos are equal
     */
    int compareTo(Object o);

    /**
     * Returns the version description.
     * @return the version description
     */
    String getDescription();

    /**
     * Returns the version number string.
     * @return the version
     */
    String getVersion();

    /**
     * Indicates whether the currently specified version string is valid.
     * @return <code>true</code> if the version string is valid;
     * <code>false</code> otherwise
     */
    boolean isVersionValid();

}
