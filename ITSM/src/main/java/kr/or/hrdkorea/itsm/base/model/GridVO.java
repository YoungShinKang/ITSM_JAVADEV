package kr.or.hrdkorea.itsm.base.model;

import java.util.List;

public class GridVO
{
  private String gridHeader;
  private String gridWidths;
  private String gridColAlign;
  private String gridColTypes;
  private String gridColSorting;
  private List rows;
  private int totalCount;
  private String page;
  private String xmlString;

  public int getTotalCount()
  {
    return this.totalCount;
  }
  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public String toString() {
    return "GridVO [gridHeader=" + this.gridHeader + ", gridWidths=" + 
      this.gridWidths + ", gridColAlign=" + this.gridColAlign + 
      ", gridColTypes=" + this.gridColTypes + ", gridColSorting=" + 
      this.gridColSorting + ", rows=" + this.rows + ", totalCount=" + 
      this.totalCount + ", page=" + this.page + ", xmlString=" + this.xmlString + 
      "]";
  }
  public String getGridHeader() {
    return this.gridHeader;
  }
  public void setGridHeader(String gridHeader) {
    this.gridHeader = gridHeader;
  }
  public String getGridWidths() {
    return this.gridWidths;
  }
  public void setGridWidths(String gridWidths) {
    this.gridWidths = gridWidths;
  }
  public String getGridColAlign() {
    return this.gridColAlign;
  }
  public void setGridColAlign(String gridColAlign) {
    this.gridColAlign = gridColAlign;
  }
  public String getGridColSorting() {
    return this.gridColSorting;
  }
  public void setGridColSorting(String gridColSorting) {
    this.gridColSorting = gridColSorting;
  }
  public String getGridColTypes() {
    return this.gridColTypes;
  }
  public void setGridColTypes(String gridColTypes) {
    this.gridColTypes = gridColTypes;
  }
  public List getRows() {
    return this.rows;
  }
  public void setRows(List rows) {
    this.rows = rows;
  }
  public String getXmlString() {
    return this.xmlString;
  }
  public void setXmlString(String xmlString) {
    this.xmlString = xmlString;
  }
  public String getPage() {
    return this.page;
  }
  public void setPage(String page) {
    this.page = page;
  }
}