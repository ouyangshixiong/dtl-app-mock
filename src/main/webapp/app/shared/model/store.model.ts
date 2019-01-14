import { Moment } from 'moment';

export interface IStore {
    id?: number;
    name?: string;
    storeType?: number;
    address?: string;
    area?: string;
    linkman?: string;
    tel?: string;
    status?: number;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class Store implements IStore {
    constructor(
        public id?: number,
        public name?: string,
        public storeType?: number,
        public address?: string,
        public area?: string,
        public linkman?: string,
        public tel?: string,
        public status?: number,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
