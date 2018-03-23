package zrprjt.ww.json;

import java.io.OutputStream;
import java.util.List;
import jxl.Workbook;
import jxl.write.WritableWorkbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;

import org.apache.log4j.Logger;

import zrprjt.ww.BasicAction;

public class TaskUpRdDXlsAction extends BasicAction {
//	private static final long serialVersionUID = 1L;
//	private SaleShipOrderD saleShipOrderD = new SaleShipOrderD();
//	private WritableWorkbook workbook;
//
//	public String execute() throws Exception {
//		try {
//			saleShipOrderD = new SaleShipOrderDFacade()
//					.findById(saleShipOrderD);
//			if (saleShipOrderD != null) {
//				WritableCellFormat wcformat = new WritableCellFormat();
//				wcformat.setAlignment(jxl.format.Alignment.CENTRE);
//				wcformat
//						.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//				wcformat.setBorder(Border.LEFT, BorderLineStyle.THIN);
//				wcformat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
//				wcformat.setBorder(Border.TOP, BorderLineStyle.THIN);
//				wcformat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
//				wcformat.setWrap(true);
//				OutputStream os = getOutputStream();
//				workbook = Workbook.createWorkbook(os);
//				WritableSheet ws = workbook.createSheet("sheet0", 0);
//
//				int col = 0;
//				int row = 1;
//
//				ws.addCell(new Label(col, row, "明细序号", wcformat));
//				ws.setColumnView(col, 20);
//				col++;
//				ws.addCell(new Label(col, row, "", wcformat));
//				ws.setColumnView(col, 60);
//				col++;
//
//				row++;
//				col = 0;
//				ws.addCell(new Label(col, row, "对应的发货单号", wcformat));
//				ws.setColumnView(col, 20);
//				col++;
//				ws.addCell(new Label(col, row, "", wcformat));
//				ws.setColumnView(col, 60);
//				col++;
//
//				row++;
//				col = 0;
//				ws.addCell(new Label(col, row, "对应的销售单号", wcformat));
//				ws.setColumnView(col, 20);
//				col++;
//				ws.addCell(new Label(col, row, "", wcformat));
//				ws.setColumnView(col, 60);
//				col++;
//
//				row++;
//				col = 0;
//				ws.addCell(new Label(col, row, "对应的销售单序号", wcformat));
//				ws.setColumnView(col, 20);
//				col++;
//				ws.addCell(new Label(col, row, "", wcformat));
//				ws.setColumnView(col, 60);
//				col++;
//
//				row++;
//				col = 0;
//				ws.addCell(new Label(col, row, "机型编码", wcformat));
//				ws.setColumnView(col, 20);
//				col++;
//				ws.addCell(new Label(col, row, "", wcformat));
//				ws.setColumnView(col, 60);
//				col++;
//
//				row++;
//				col = 0;
//				ws.addCell(new Label(col, row, "数量", wcformat));
//				ws.setColumnView(col, 20);
//				col++;
//				ws.addCell(new Label(col, row, "", wcformat));
//				ws.setColumnView(col, 60);
//				col++;
//
//				row++;
//				col = 0;
//				ws.addCell(new Label(col, row, "物流单号", wcformat));
//				ws.setColumnView(col, 20);
//				col++;
//				ws.addCell(new Label(col, row, "", wcformat));
//				ws.setColumnView(col, 60);
//				col++;
//
//				row++;
//				col = 0;
//
//				//				
//				// WritableSheet ws2 = workbook.createSheet("sheet1", 0);
//				//				
//				// int col2 = 0;
//				// int row2 = 1;
//				//				
//				// ws2.addCell(new Label(col2,row2,"包含的串码",wcformat));
//				// ws2.setColumnView(col2,20);
//				// col2++;
//				// ws2.addCell(new Label(col2,row2,"",wcformat));
//				// ws2.setColumnView(col2,60);
//				// col2++;
//			}
//		} catch (Exception e) {
//			this.sendMessage(MSG.F_SEA, "SaleShipOrderDList.shtml");
//			Logger.getLogger(this.getClass()).error(
//					"SaleShipOrderDViewAction execute Exception", e);
//			return ERROR;
//		}
//		return SUCCESS;
//	}
//
//	public String exp() throws Exception {
//		try {
////			List<SaleShipOrderD> saleShipOrderDs = new SaleShipOrderDFacade()
////					.find(saleShipOrderD);
////			if (saleShipOrderDs != null) {
//
//				WritableCellFormat wcformat = new WritableCellFormat();
//				wcformat.setAlignment(jxl.format.Alignment.CENTRE);
//				wcformat
//						.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//				wcformat.setBorder(Border.LEFT, BorderLineStyle.THIN);
//				wcformat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
//				wcformat.setBorder(Border.TOP, BorderLineStyle.THIN);
//				wcformat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
//				wcformat.setWrap(true);
//				OutputStream os = getOutputStream();
//				workbook = Workbook.createWorkbook(os);
//
//				WritableSheet ws2 = workbook.createSheet("发货单明细包含的串码", 0);
//
//				int col2 = 0;
//				int row2 = 0;
//
//				ws2.addCell(new Label(col2, row2, "对应的发货单明细序号", wcformat));
//				ws2.setColumnView(col2, 20);
//				col2++;
//
//				ws2.addCell(new Label(col2, row2, "串码", wcformat));
//				ws2.setColumnView(col2, 20);
//				col2++;
//				
//				
//				
//
//				WritableSheet ws = workbook.createSheet("发货单明细", 0);
//				int index = 0;
//
//				ws.addCell(new Label(index, 0, "序号", wcformat));
//				ws.setColumnView(index, 20);
//				index++;
//				ws.addCell(new Label(index, 0, "机型编码", wcformat));
//				ws.setColumnView(index, 20);
//				index++;
//				ws.addCell(new Label(index, 0, "数量", wcformat));
//				ws.setColumnView(index, 20);
//				index++;
//				ws.addCell(new Label(index, 0, "物流单号", wcformat));
//				ws.setColumnView(index, 20);
//				index++;
//				ws.addCell(new Label(index, 0, "对应的订单编号", wcformat));
//				ws.setColumnView(index, 20);
//				index++;
//				ws.addCell(new Label(index, 0, "对应的订单明细序号", wcformat));
//				ws.setColumnView(index, 20);
//				index++;
////				int row = 2;
////				for (int i = 0; i < saleShipOrderDs.size(); i++) {
////					row++;
////					int cols = 0;
////					if (saleShipOrderDs.get(i).getItemNo() != null) {
////						ws.addCell(new Label(cols, row, "", wcformat));
////						cols++;
////					} else {
////						ws.addCell(new Label(cols, row, "", wcformat));
////						cols++;
////					}
////					if (saleShipOrderDs.get(i).getRefSSMsgNo() != null) {
////						ws.addCell(new Label(cols, row, "", wcformat));
////						cols++;
////					} else {
////						ws.addCell(new Label(cols, row, "", wcformat));
////						cols++;
////					}
////					if (saleShipOrderDs.get(i).getRefSOMsgNo() != null) {
////						ws.addCell(new Label(cols, row, "", wcformat));
////						cols++;
////					} else {
////						ws.addCell(new Label(cols, row, "", wcformat));
////						cols++;
////					}
////					if (saleShipOrderDs.get(i).getRefSOLineID() != null) {
////						ws.addCell(new Label(cols, row, "", wcformat));
////						cols++;
////					} else {
////						ws.addCell(new Label(cols, row, "", wcformat));
////						cols++;
////					}
////					if (saleShipOrderDs.get(i).getPartNo() != null) {
////						ws.addCell(new Label(cols, row, "", wcformat));
////						cols++;
////					} else {
////						ws.addCell(new Label(cols, row, "", wcformat));
////						cols++;
////					}
////					if (saleShipOrderDs.get(i).getQty() != null) {
////						ws.addCell(new Label(cols, row, "", wcformat));
////						cols++;
////					} else {
////						ws.addCell(new Label(cols, row, "", wcformat));
////						cols++;
////					}
//
//					// if(saleShipOrderDs.get(i).getLogisticsNo() != null) {
//					// ws.addCell(new Label(cols,row,"",wcformat));
//					// cols++;
//					// }
//					// else {
//					// ws.addCell(new Label(cols,row,"",wcformat));
//					// cols++;
//					// }
////				}
//
////			}
//			this.sendMessage(MSG.S_EXP, "SaleShipOrderDList.shtml");
//		} catch (Exception e) {
//			this.sendMessage(MSG.F_EXP, "SaleShipOrderDList.shtml");
//			Logger.getLogger(this.getClass()).error(
//					"SaleShipOrderDListAction execute Exception", e);
//			return ERROR;
//		}
//		return SUCCESS;
//	}
//
//	public SaleShipOrderD getSaleShipOrderD() {
//		return saleShipOrderD;
//	}
//
//	public void setSaleShipOrderD(SaleShipOrderD saleShipOrderD) {
//		this.saleShipOrderD = saleShipOrderD;
//	}
//
//	public WritableWorkbook getWorkbook() {
//		return workbook;
//	}
//
//	public void setWorkbook(WritableWorkbook workbook) {
//		this.workbook = workbook;
//	}
}
