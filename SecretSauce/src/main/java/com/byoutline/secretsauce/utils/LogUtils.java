/*
 * Copyright 2012 Google Inc.
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

package com.byoutline.secretsauce.utils;

import android.util.Log;

import com.byoutline.secretsauce.SecretSauceSettings;


/**
 * Helper methods that make logging more consistent throughout the app. <br/>
 * Consider using <a href="https://github.com/JakeWharton/timber">Timber</a> instead.
 */
public class LogUtils {

    private static final int MAX_LOG_TAG_LENGTH = 23;

    private LogUtils() {
    }

    public static String makeLogTag(String str) {
        String prefix = SecretSauceSettings.INSTANCE.getLogPrefix();
        int logPrefixLength = prefix.length();
        if (str.length() > MAX_LOG_TAG_LENGTH - logPrefixLength) {
            return prefix + str.substring(0, MAX_LOG_TAG_LENGTH - logPrefixLength - 1);
        }

        return prefix + str;
    }

    /**
     * WARNING: Don't use this when obfuscating class names with Proguard!
     */
    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static String internalMakeLogTag(Class cls) {
        return SecretSauceSettings.INSTANCE.getLogPrefix() + makeLogTag(cls);
    }

    public static void LOGD(final String tag, String message) {
        if (SecretSauceSettings.INSTANCE.getDEBUG()) {
            Log.d(tag, message);
        }
    }

    public static void LOGD(final String tag, String message, Throwable cause) {
        if (SecretSauceSettings.INSTANCE.getDEBUG()) {
            Log.d(tag, message, cause);
        }
    }

    public static void LOGV(final String tag, String message) {
        // noinspection PointlessBooleanExpression,ConstantConditions
        if (SecretSauceSettings.INSTANCE.getDEBUG()) {
            Log.v(tag, message);
        }
    }

    public static void LOGV(final String tag, String message, Throwable cause) {
        // noinspection PointlessBooleanExpression,ConstantConditions
        if (SecretSauceSettings.INSTANCE.getDEBUG()) {
            Log.v(tag, message, cause);
        }
    }

    public static void LOGI(final String tag, String message) {
        Log.i(tag, message);
    }

    public static void LOGI(final String tag, String message, Throwable cause) {
        Log.i(tag, message, cause);
    }

    public static void LOGW(final String tag, String message) {
        Log.w(tag, message);
    }

    public static void LOGW(final String tag, String message, Throwable cause) {
        Log.w(tag, message, cause);
    }

    public static void LOGE(final String tag, String message) {
        Log.e(tag, message);

    }

    public static void LOGE(final String tag, Throwable cause) {
        Log.e(tag, "Error:", cause);
    }

    public static void LOGE(final String tag, String message, Throwable cause) {
        Log.e(tag, message, cause);
    }
}
