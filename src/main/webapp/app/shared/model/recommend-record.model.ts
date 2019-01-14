import { Moment } from 'moment';

export interface IRecommendRecord {
    id?: number;
    recUserId?: number;
    userId?: number;
    status?: number;
    identityFlag?: string;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class RecommendRecord implements IRecommendRecord {
    constructor(
        public id?: number,
        public recUserId?: number,
        public userId?: number,
        public status?: number,
        public identityFlag?: string,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
