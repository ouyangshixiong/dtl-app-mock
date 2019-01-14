import { Moment } from 'moment';

export interface IProductRecord {
    id?: number;
    txnId?: string;
    title?: string;
    categoryId?: number;
    standardId?: number;
    storeId?: number;
    productImg?: string;
    unitPrice?: number;
    stock?: number;
    minDeal?: number;
    depotAddress?: string;
    contactName?: string;
    mobile?: string;
    description?: string;
    status?: number;
    objection?: string;
    recordName?: string;
    createBy?: string;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class ProductRecord implements IProductRecord {
    constructor(
        public id?: number,
        public txnId?: string,
        public title?: string,
        public categoryId?: number,
        public standardId?: number,
        public storeId?: number,
        public productImg?: string,
        public unitPrice?: number,
        public stock?: number,
        public minDeal?: number,
        public depotAddress?: string,
        public contactName?: string,
        public mobile?: string,
        public description?: string,
        public status?: number,
        public objection?: string,
        public recordName?: string,
        public createBy?: string,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
