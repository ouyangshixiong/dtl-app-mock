import { Moment } from 'moment';

export interface IProduct {
    id?: number;
    txnId?: string;
    title?: string;
    categoryId?: number;
    standardId?: number;
    storeId?: number;
    productImg?: string;
    unitPrice?: number;
    amount?: number;
    stock?: number;
    minDeal?: number;
    depotAddress?: string;
    contactName?: string;
    mobile?: string;
    description?: string;
    status?: number;
    createBy?: string;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class Product implements IProduct {
    constructor(
        public id?: number,
        public txnId?: string,
        public title?: string,
        public categoryId?: number,
        public standardId?: number,
        public storeId?: number,
        public productImg?: string,
        public unitPrice?: number,
        public amount?: number,
        public stock?: number,
        public minDeal?: number,
        public depotAddress?: string,
        public contactName?: string,
        public mobile?: string,
        public description?: string,
        public status?: number,
        public createBy?: string,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
