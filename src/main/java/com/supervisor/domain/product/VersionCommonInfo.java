package com.supervisor.domain.product;

import java.time.LocalDate;

public interface VersionCommonInfo {

    public String getVersion();

    public void setVersion(String version);

    public String getDescription();

    public void setDescription(String description);

    public LocalDate getReleaseDate();

    public void setReleaseDate(LocalDate releaseDate);

    public String getNotes();

    public void setNotes(String notes);
}
