import { Moment } from 'moment';

export interface IImage {
    id?: number;
    name?: string;
    url?: string;
    size?: string;
    pixel?: string;
    format?: string;
    originalFileName?: string;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class Image implements IImage {
    constructor(
        public id?: number,
        public name?: string,
        public url?: string,
        public size?: string,
        public pixel?: string,
        public format?: string,
        public originalFileName?: string,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
