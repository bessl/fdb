package model;

import java.io.Serializable;
import java.util.Date;

public class ModelBase implements Serializable
{
 	private static final long serialVersionUID = 1L;
	protected Long id = null;
	protected Date lastModifiedTimestamp;
	protected boolean dirty = false;
		
  public Long getId() 
  {
	  return id;
  }

  public void setId(Long id) 
  {
	if (this.id != null) 
	{
	  throw new IllegalStateException("entity has already an primary key!");
	}
	this.id = id;
  }

  public Date getLastModifiedTimestamp() 
  {
    return lastModifiedTimestamp;
  }

  public void setLastModifiedTimestamp(Date lastModifiedTimestamp)
  {
    this.lastModifiedTimestamp = lastModifiedTimestamp;
  }

  public boolean isNew()
  {
    return getId() == null;
  }

  public boolean isDirty()
  {
    return dirty;
  }

  public void markAsClean()
  {
    dirty = false;
  }

  public void markAsDirty()
  {
    dirty = true;
  }

  @Override
  public String toString()
  {
	StringBuilder sb = new StringBuilder();
	sb
	.append(getClass().getSimpleName())
	.append("{")
	.append("id=")
	.append(id)
	.append("lastModifiedTimestamp=")
	.append(lastModifiedTimestamp)
	.append("}");
	return sb.toString();
  }
}
