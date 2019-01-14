import { Moment } from 'moment';

export interface IEnterpriseInfo {
    id?: number;
    userId?: number;
    name?: string;
    legalPersonName?: string;
    legalPersonIdCardNum?: string;
    legalPersonMobile?: string;
    enterpriseTel?: string;
    businessLicenseImgId?: number;
    status?: number;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class EnterpriseInfo implements IEnterpriseInfo {
    constructor(
        public id?: number,
        public userId?: number,
        public name?: string,
        public legalPersonName?: string,
        public legalPersonIdCardNum?: string,
        public legalPersonMobile?: string,
        public enterpriseTel?: string,
        public businessLicenseImgId?: number,
        public status?: number,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
