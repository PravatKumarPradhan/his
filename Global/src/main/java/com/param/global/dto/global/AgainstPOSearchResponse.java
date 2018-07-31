package com.param.global.dto.global;

import java.util.Date;
import java.util.List;

public class AgainstPOSearchResponse extends AgainstPurchaseOrder{
	
	    private Integer id;
	
	    private String storeName;
	    
	    private String poNo;

        private Date poDate;
        
        List<PurchaseOrderDetailResponse> items;

		public AgainstPOSearchResponse() {
			super();
		}

		public AgainstPOSearchResponse(Integer id,String vendorName, Integer storeId,String storeName, String poNo, Date poDate) {
			super(vendorName,storeId);
			this.id = id;
			this.storeName = storeName;
			this.poNo = poNo;
			this.poDate = poDate;
		}
		
		public AgainstPOSearchResponse(Integer id, String poNo) {
			super();
			this.id = id;
			this.poNo = poNo;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getStoreName() {
			return storeName;
		}

		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}

		public String getPoNo() {
			return poNo;
		}

		public void setPoNo(String poNo) {
			this.poNo = poNo;
		}

		public Date getPoDate() {
			return poDate;
		}

		public void setPoDate(Date poDate) {
			this.poDate = poDate;
		}

		public List<PurchaseOrderDetailResponse> getItems() {
			return items;
		}

		public void setItems(List<PurchaseOrderDetailResponse> items) {
			this.items = items;
		}
        
        
		
		
        

}
