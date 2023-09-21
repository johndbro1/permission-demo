package net.paclabs.common.webapp.api;

public interface IVersion {

    String getVersionId();
    String getGroupId();
    String getArtifactId();
    String getTimestamp();
    String getRevision();
    
}
