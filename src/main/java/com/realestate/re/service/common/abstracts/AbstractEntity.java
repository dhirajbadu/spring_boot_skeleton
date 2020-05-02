package com.realestate.re.service.common.abstracts;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

@MappedSuperclass
public abstract class AbstractEntity<PK extends Serializable> extends AbstractPersistable<PK> {

	private static final long serialVersionUID = 8453654076725018243L;

	@Basic(optional = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created = new Date();

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;

	@Version
	@Column
	private int version;

	public AbstractEntity() {
		setCreated(new Date());
	}

	@PreUpdate
	public void preUpdate() {
		lastModified = new Date();
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getVersion() {
		return version;
	}

}

