package com.supervisor.domain.product;

import java.time.LocalDate;

public interface VersionCommonInfo {

    String getVersion();

    void setVersion(String version);

    String getDescription();

    void setDescription(String description);

    LocalDate getReleaseDate();

    void setReleaseDate(LocalDate releaseDate);

    String getNotes();

    void setNotes(String notes);
}
