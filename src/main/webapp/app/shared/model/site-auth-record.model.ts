import { Moment } from 'moment';

export interface ISiteAuthRecord {
    id?: number;
    txnId?: string;
    userId?: number;
    type?: number;
    address?: string;
    siteImgIds?: string;
    authStatus?: number;
    auditOpinion?: string;
    auditStaffName?: string;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class SiteAuthRecord implements ISiteAuthRecord {
    constructor(
        public id?: number,
        public txnId?: string,
        public userId?: number,
        public type?: number,
        public address?: string,
        public siteImgIds?: string,
        public authStatus?: number,
        public auditOpinion?: string,
        public auditStaffName?: string,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
