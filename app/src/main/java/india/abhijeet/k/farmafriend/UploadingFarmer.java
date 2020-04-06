package india.abhijeet.k.farmafriend;

public class UploadingFarmer {

      private String mName;
      private String mAdress;
      private  String mGatNo;
    private  String mLandName;
    private  String mLandArea;
    private  String mSugarcaneName;
    private String mDate;
    private String mFarmImgUri;
    private String mSatBaraImgUri;

    private String mcurLocation;
    private String mFormStatus;

    private String mFormRemarks;

    private String mUsrId;

    public UploadingFarmer()
    {   }                     //empty Constructor



    public UploadingFarmer(String userid,String name,String address,String gatno,String landName,String landArea,String sugaecaneName,String date,String farmimgUri,String satbaraUri,String curLocation,String FormStatus,String formRemarks)
    {

        mUsrId=userid;
        mName=name;
        mAdress=address;
        mGatNo=gatno;
        mLandName=landName;
        mLandArea=landArea;
        mSugarcaneName=sugaecaneName;
        mDate=date;
        mFarmImgUri=farmimgUri;
        mSatBaraImgUri=satbaraUri;
        mcurLocation=curLocation;
        mFormStatus=FormStatus;

        mFormRemarks=formRemarks;
    }


//////////getter and setter methods ...

    public String getUsrId()
    {
        return mUsrId;
    }


    public String getName()
    {
        return mName;
    }

    public String getAdress() {
        return mAdress;
    }

    public String getGatNo() {
        return mGatNo;
    }

    public String getLandName() {
        return mLandName;
    }

    public String getLandArea() {
        return mLandArea;
    }

    public String getSugaecaneName() {
        return mSugarcaneName;
    }

    public String getDate() {
        return mDate;
    }


    public String getFarmImgUri() {
        return mFarmImgUri;
    }

    public String getSatBaraImgUri() {
        return mSatBaraImgUri;
    }

    public String getcurLocation() {
        return mcurLocation;
    }

    public String getFormStatus() {
        return mFormStatus;
    }

    public String getFormRemarks() {
        return mFormRemarks;
    }


    ///////////setter methods//////

    public void setUsrId(String userid) {
        mUsrId = userid;
    }


    public void setName(String name) {
        mName = name;
    }


    public void setAdress(String address) {
        mAdress = address;
    }


    public void setGatNo(String gatno) {
        mGatNo = gatno;
    }


    public void setLandName(String landName) {
        mLandName = landName;
    }


    public void setLandArea(String landArea) {
        mLandArea = landArea;
    }

    public void setSugarcaneName(String sugaecaneName) {
        mSugarcaneName =sugaecaneName.trim();
    }


    public void setDate(String date) {
        mDate = date;
    }


    public void setFarmImgUri(String farmImgUri) {
        mFarmImgUri = farmImgUri;
    }


    public void setSatBaraImgUri(String satBaraImgUri) {
        mSatBaraImgUri = satBaraImgUri;
    }


    public void setcurLocation(String curLocation) {
        mcurLocation = curLocation;
    }
    public void setFormStatus(String FormStatus) {
        mFormStatus = FormStatus;
    }

    public void setFormRemarks(String formRemarks) {
        mFormRemarks=formRemarks;
    }


}
