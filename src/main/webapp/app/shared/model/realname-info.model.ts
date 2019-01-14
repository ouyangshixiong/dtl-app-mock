import { Moment } from 'moment';

export interface IRealnameInfo {
    id?: number;
    userId?: number;
    realName?: string;
    idCardNum?: string;
    idCardImgIdA?: number;
    idCardImgIdB?: number;
    status?: number;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class RealnameInfo implements IRealnameInfo {
    constructor(
        public id?: number,
        public userId?: number,
        public realName?: string,
        public idCardNum?: string,
        public idCardImgIdA?: number,
        public idCardImgIdB?: number,
        public status?: number,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
