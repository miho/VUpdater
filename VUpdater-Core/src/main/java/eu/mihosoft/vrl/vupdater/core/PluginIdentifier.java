/* 
 * PluginIdentifier.java
 * 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2009–2012 Steinbeis Forschungszentrum (STZ Ölbronn),
 * Copyright (c) 2006–2012 by Michael Hoffer
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
 * First, the following text must be displayed on the Canvas:
 * "based on VRL source code". In this case the VRL canvas icon must be removed.
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
 * Computing and Visualization in Science, 2011, in press.
 */

package eu.mihosoft.vrl.vupdater.core;

import java.util.regex.Pattern;

/**
 * Unique plugin identifier.
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
final class PluginIdentifier {

    private final VersionInfo version;
    private final String name;
    
    /**
     * Pattern to match plugin names.
     */
    public static final Pattern PLUGIN_NAME =
            Pattern.compile("[A-Za-z0-9-+_]+");

    /**
     * Constructor.
     * @param name plugin name
     * @param version plugin version
     */
    public PluginIdentifier(String name, VersionInfo version) {
        this.version = version;
        this.name = name;
    }

    /**
     * Constructor.
     * @param name plugin name
     * @param version plugin version
     */
    public PluginIdentifier(String name, String version) {
        this.version = new VersionInfo(version);
        this.name = name;
    }

    /**
     * Returns the plugin verison.
     * @return the plugin version
     */
    public VersionInfo getVersion() {
        return version;
    }

    /**
     * Returns the plugin name.
     * @return the plugin name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "-" + version.getVersion();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.version != null ? this.version.hashCode() : 0);
        hash = 13 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof PluginIdentifier) {
            PluginIdentifier p = (PluginIdentifier) o;
            if (p.getName().equals(getName())
                    && p.getVersion().equals(getVersion())) {
                return true;
            }
        }

        return false;
    }
    
    public boolean isNameValid() {
        
        return PLUGIN_NAME.matcher(name).matches();
    }
    
    public boolean isVersionValid() {
        return version.isVersionValid();
    }
}
