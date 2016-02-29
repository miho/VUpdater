/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.vupdater.core;

import java.io.File;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public interface DownloadResult {

    File getFile();

    default boolean hasASC() {return false;};

    default boolean hasSHA1(){return false;};

    boolean isValid();
}
