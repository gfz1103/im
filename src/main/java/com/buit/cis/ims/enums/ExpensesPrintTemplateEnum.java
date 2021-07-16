package com.buit.cis.ims.enums;

/**
 * @Description 费用清单报表打印模板格式枚举
 * @Author jiangwei
 * @Date 2021/04/22
 */
public enum ExpensesPrintTemplateEnum {
    DETAIL_LIST(1, "明细格式", "Expenses_DetailList.jasper"),
    ITEM_LIST(2, "项目汇总", "Expenses_SummarizedByItem.jasper"),
    ADVICE_LIST(3, "医嘱格式", "Expenses_AdviceList.jasper"),
    DATE_LIST(4, "按日汇总", "Expenses_SummarizedByDate.jasper");

    private int value;
    private String text;
    private String url;

    ExpensesPrintTemplateEnum(int value, String text, String url) {
        this.value = value;
        this.text = text;
        this.url = url;
    }

    public static String getTemplateUrl(int value) {
        ExpensesPrintTemplateEnum[] enums = ExpensesPrintTemplateEnum.values();
        for (ExpensesPrintTemplateEnum e : enums) {
            if (e.equals(value)) {
                return e.getUrl();
            }
        }
        return null;
    }

    //参数类型不能改为int 否则Integer类型入参会调用enum类equals(Object o)方法，而非本方法
    public boolean equals(Integer value) {
        return this.value == value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
