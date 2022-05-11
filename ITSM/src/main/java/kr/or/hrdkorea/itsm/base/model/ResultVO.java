package kr.or.hrdkorea.itsm.base.model;

import java.util.HashMap;

public class ResultVO
{
  private GridVO gridVO;
  private HashMap resultMap;
  private String resultString;
  private int resultInt;
  private boolean resultBoolean;
  private boolean isSuccess = true;
  private String resultMsg;

  public GridVO getGridVO()
  {
    return this.gridVO;
  }
  public void setGridVO(GridVO gridVO) {
    this.gridVO = gridVO;
  }

  public HashMap getResultMap() {
    return this.resultMap;
  }
  public void setResultMap(HashMap resultMap) {
    this.resultMap = resultMap;
  }
  
  public String getResultString() {
    return this.resultString;
  }
  public void setResultString(String resultString) {
    this.resultString = resultString;
  }
  public int getResultInt() {
    return this.resultInt;
  }
  public void setResultInt(int resultInt) {
    this.resultInt = resultInt;
  }
  public boolean isResultBoolean() {
    return this.resultBoolean;
  }
  public void setResultBoolean(boolean resultBoolean) {
    this.resultBoolean = resultBoolean;
  }
  public boolean isSuccess() {
    return this.isSuccess;
  }
  public void setSuccess(boolean isSuccess) {
    this.isSuccess = isSuccess;
  }
  public String getResultMsg() {
    return this.resultMsg;
  }
  public void setResultMsg(String resultMsg) {
    this.resultMsg = resultMsg;
  }
}