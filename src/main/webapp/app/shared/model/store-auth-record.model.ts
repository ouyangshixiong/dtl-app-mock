import { Moment } from 'moment';

export interface IStoreAuthRecord {
    id?: number;
    txnId?: string;
    name?: string;
    storeType?: number;
    address?: string;
    area?: string;
    linkman?: string;
    tel?: string;
    authStatus?: number;
    auditOpinion?: string;
    auditStaffName?: string;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class StoreAuthRecord implements IStoreAuthRecord {
    constructor(
        public id?: number,
        public txnId?: string,
        public name?: string,
        public storeType?: number,
        public address?: string,
        public area?: string,
        public linkman?: string,
        public tel?: string,
        public authStatus?: number,
        public auditOpinion?: string,
        public auditStaffName?: string,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
