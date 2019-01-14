import { Moment } from 'moment';

export interface IEnterpriseAuthRecord {
    id?: number;
    txnId?: string;
    userId?: number;
    name?: string;
    legalPersonName?: string;
    legalPersonIdCardNum?: string;
    legalPersonMobile?: string;
    enterpriseTel?: string;
    businessLicenseImgId?: number;
    authStatus?: number;
    auditOpinion?: string;
    auditStaffName?: string;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class EnterpriseAuthRecord implements IEnterpriseAuthRecord {
    constructor(
        public id?: number,
        public txnId?: string,
        public userId?: number,
        public name?: string,
        public legalPersonName?: string,
        public legalPersonIdCardNum?: string,
        public legalPersonMobile?: string,
        public enterpriseTel?: string,
        public businessLicenseImgId?: number,
        public authStatus?: number,
        public auditOpinion?: string,
        public auditStaffName?: string,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
