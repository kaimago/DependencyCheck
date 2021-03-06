/*
 * This file is part of dependency-check-ant.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) 2015 Jeremy Long. All Rights Reserved.
 */
package org.owasp.dependencycheck.taskdefs;

import org.apache.tools.ant.BuildException;
import org.owasp.dependencycheck.Engine;
import org.owasp.dependencycheck.data.nvdcve.DatabaseException;
import org.owasp.dependencycheck.utils.Settings;
import org.slf4j.impl.StaticLoggerBinder;

/**
 * An Ant task definition to execute dependency-check update. This will download the latest data from the National Vulnerability
 * Database (NVD) and store a copy in the local database.
 *
 * @author Jeremy Long
 */
public class Update extends Purge {

    /**
     * Construct a new UpdateTask.
     */
    public Update() {
        super();
        // Call this before Dependency Check Core starts logging anything - this way, all SLF4J messages from
        // core end up coming through this tasks logger
        StaticLoggerBinder.getSingleton().setTask(this);
    }

    /**
     * The Proxy Server.
     */
    private String proxyServer;

    /**
     * Get the value of proxyServer.
     *
     * @return the value of proxyServer
     */
    public String getProxyServer() {
        return proxyServer;
    }

    /**
     * Set the value of proxyServer.
     *
     * @param server new value of proxyServer
     */
    public void setProxyServer(String server) {
        this.proxyServer = server;
    }

    /**
     * The Proxy Port.
     */
    private String proxyPort;

    /**
     * Get the value of proxyPort.
     *
     * @return the value of proxyPort
     */
    public String getProxyPort() {
        return proxyPort;
    }

    /**
     * Set the value of proxyPort.
     *
     * @param proxyPort new value of proxyPort
     */
    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }
    /**
     * The Proxy username.
     */
    private String proxyUsername;

    /**
     * Get the value of proxyUsername.
     *
     * @return the value of proxyUsername
     */
    public String getProxyUsername() {
        return proxyUsername;
    }

    /**
     * Set the value of proxyUsername.
     *
     * @param proxyUsername new value of proxyUsername
     */
    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }
    /**
     * The Proxy password.
     */
    private String proxyPassword;

    /**
     * Get the value of proxyPassword.
     *
     * @return the value of proxyPassword
     */
    public String getProxyPassword() {
        return proxyPassword;
    }

    /**
     * Set the value of proxyPassword.
     *
     * @param proxyPassword new value of proxyPassword
     */
    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }
    /**
     * The Connection Timeout.
     */
    private String connectionTimeout;

    /**
     * Get the value of connectionTimeout.
     *
     * @return the value of connectionTimeout
     */
    public String getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * Set the value of connectionTimeout.
     *
     * @param connectionTimeout new value of connectionTimeout
     */
    public void setConnectionTimeout(String connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
    /**
     * The database driver name; such as org.h2.Driver.
     */
    private String databaseDriverName;

    /**
     * Get the value of databaseDriverName.
     *
     * @return the value of databaseDriverName
     */
    public String getDatabaseDriverName() {
        return databaseDriverName;
    }

    /**
     * Set the value of databaseDriverName.
     *
     * @param databaseDriverName new value of databaseDriverName
     */
    public void setDatabaseDriverName(String databaseDriverName) {
        this.databaseDriverName = databaseDriverName;
    }

    /**
     * The path to the database driver JAR file if it is not on the class path.
     */
    private String databaseDriverPath;

    /**
     * Get the value of databaseDriverPath.
     *
     * @return the value of databaseDriverPath
     */
    public String getDatabaseDriverPath() {
        return databaseDriverPath;
    }

    /**
     * Set the value of databaseDriverPath.
     *
     * @param databaseDriverPath new value of databaseDriverPath
     */
    public void setDatabaseDriverPath(String databaseDriverPath) {
        this.databaseDriverPath = databaseDriverPath;
    }
    /**
     * The database connection string.
     */
    private String connectionString;

    /**
     * Get the value of connectionString.
     *
     * @return the value of connectionString
     */
    public String getConnectionString() {
        return connectionString;
    }

    /**
     * Set the value of connectionString.
     *
     * @param connectionString new value of connectionString
     */
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
    /**
     * The user name for connecting to the database.
     */
    private String databaseUser;

    /**
     * Get the value of databaseUser.
     *
     * @return the value of databaseUser
     */
    public String getDatabaseUser() {
        return databaseUser;
    }

    /**
     * Set the value of databaseUser.
     *
     * @param databaseUser new value of databaseUser
     */
    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }

    /**
     * The password to use when connecting to the database.
     */
    private String databasePassword;

    /**
     * Get the value of databasePassword.
     *
     * @return the value of databasePassword
     */
    public String getDatabasePassword() {
        return databasePassword;
    }

    /**
     * Set the value of databasePassword.
     *
     * @param databasePassword new value of databasePassword
     */
    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    /**
     * The url for the modified NVD CVE (1.2 schema).
     */
    private String cveUrl12Modified;

    /**
     * Get the value of cveUrl12Modified.
     *
     * @return the value of cveUrl12Modified
     */
    public String getCveUrl12Modified() {
        return cveUrl12Modified;
    }

    /**
     * Set the value of cveUrl12Modified.
     *
     * @param cveUrl12Modified new value of cveUrl12Modified
     */
    public void setCveUrl12Modified(String cveUrl12Modified) {
        this.cveUrl12Modified = cveUrl12Modified;
    }

    /**
     * The url for the modified NVD CVE (2.0 schema).
     */
    private String cveUrl20Modified;

    /**
     * Get the value of cveUrl20Modified.
     *
     * @return the value of cveUrl20Modified
     */
    public String getCveUrl20Modified() {
        return cveUrl20Modified;
    }

    /**
     * Set the value of cveUrl20Modified.
     *
     * @param cveUrl20Modified new value of cveUrl20Modified
     */
    public void setCveUrl20Modified(String cveUrl20Modified) {
        this.cveUrl20Modified = cveUrl20Modified;
    }

    /**
     * Base Data Mirror URL for CVE 1.2.
     */
    private String cveUrl12Base;

    /**
     * Get the value of cveUrl12Base.
     *
     * @return the value of cveUrl12Base
     */
    public String getCveUrl12Base() {
        return cveUrl12Base;
    }

    /**
     * Set the value of cveUrl12Base.
     *
     * @param cveUrl12Base new value of cveUrl12Base
     */
    public void setCveUrl12Base(String cveUrl12Base) {
        this.cveUrl12Base = cveUrl12Base;
    }

    /**
     * Data Mirror URL for CVE 2.0.
     */
    private String cveUrl20Base;

    /**
     * Get the value of cveUrl20Base.
     *
     * @return the value of cveUrl20Base
     */
    public String getCveUrl20Base() {
        return cveUrl20Base;
    }

    /**
     * Set the value of cveUrl20Base.
     *
     * @param cveUrl20Base new value of cveUrl20Base
     */
    public void setCveUrl20Base(String cveUrl20Base) {
        this.cveUrl20Base = cveUrl20Base;
    }

    /**
     * The number of hours to wait before re-checking for updates.
     */
    private Integer cveValidForHours;

    /**
     * Get the value of cveValidForHours.
     *
     * @return the value of cveValidForHours
     */
    public Integer getCveValidForHours() {
        return cveValidForHours;
    }

    /**
     * Set the value of cveValidForHours.
     *
     * @param cveValidForHours new value of cveValidForHours
     */
    public void setCveValidForHours(Integer cveValidForHours) {
        this.cveValidForHours = cveValidForHours;
    }

    /**
     * Executes the update by initializing the settings, downloads the NVD XML data, and then processes the data storing it in the
     * local database.
     *
     * @throws BuildException thrown if a connection to the local database cannot be made.
     */
    @Override
    public void execute() throws BuildException {
        populateSettings();
        Engine engine = null;
        try {
            engine = new Engine(Update.class.getClassLoader());
            engine.doUpdates();
        } catch (DatabaseException ex) {
            throw new BuildException("Unable to connect to the dependency-check database; unable to update the NVD data", ex);
        } finally {
            Settings.cleanup(true);
            if (engine != null) {
                engine.cleanup();
            }
        }
    }

    /**
     * Takes the properties supplied and updates the dependency-check settings. Additionally, this sets the system properties
     * required to change the proxy server, port, and connection timeout.
     *
     * @throws BuildException thrown when an invalid setting is configured.
     */
    @Override
    protected void populateSettings() throws BuildException {
        super.populateSettings();
        Settings.setStringIfNotEmpty(Settings.KEYS.PROXY_SERVER, proxyServer);
        Settings.setStringIfNotEmpty(Settings.KEYS.PROXY_PORT, proxyPort);
        Settings.setStringIfNotEmpty(Settings.KEYS.PROXY_USERNAME, proxyUsername);
        Settings.setStringIfNotEmpty(Settings.KEYS.PROXY_PASSWORD, proxyPassword);
        Settings.setStringIfNotEmpty(Settings.KEYS.CONNECTION_TIMEOUT, connectionTimeout);
        Settings.setStringIfNotEmpty(Settings.KEYS.DB_DRIVER_NAME, databaseDriverName);
        Settings.setStringIfNotEmpty(Settings.KEYS.DB_DRIVER_PATH, databaseDriverPath);
        Settings.setStringIfNotEmpty(Settings.KEYS.DB_CONNECTION_STRING, connectionString);
        Settings.setStringIfNotEmpty(Settings.KEYS.DB_USER, databaseUser);
        Settings.setStringIfNotEmpty(Settings.KEYS.DB_PASSWORD, databasePassword);
        Settings.setStringIfNotEmpty(Settings.KEYS.CVE_MODIFIED_12_URL, cveUrl12Modified);
        Settings.setStringIfNotEmpty(Settings.KEYS.CVE_MODIFIED_20_URL, cveUrl20Modified);
        Settings.setStringIfNotEmpty(Settings.KEYS.CVE_SCHEMA_1_2, cveUrl12Base);
        Settings.setStringIfNotEmpty(Settings.KEYS.CVE_SCHEMA_2_0, cveUrl20Base);
        if (cveValidForHours != null) {
            if (cveValidForHours >= 0) {
                Settings.setInt(Settings.KEYS.CVE_CHECK_VALID_FOR_HOURS, cveValidForHours);
            } else {
                throw new BuildException("Invalid setting: `cpeValidForHours` must be 0 or greater");
            }
        }
    }
}
