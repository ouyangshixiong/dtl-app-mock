import { Moment } from 'moment';

export interface ISiteInfo {
    id?: number;
    userId?: number;
    siteType?: number;
    address?: string;
    siteImgIds?: string;
    status?: number;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class SiteInfo implements ISiteInfo {
    constructor(
        public id?: number,
        public userId?: number,
        public siteType?: number,
        public address?: string,
        public siteImgIds?: string,
        public status?: number,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
