import { Moment } from 'moment';

export interface IDTLUser {
    id?: number;
    userRoleId?: number;
    name?: string;
    account?: string;
    password?: string;
    status?: number;
    userType?: number;
    userIdentity?: number;
    source?: number;
    ipAddr?: string;
    regTime?: Moment;
    lastLoginTime?: Moment;
}

export class DTLUser implements IDTLUser {
    constructor(
        public id?: number,
        public userRoleId?: number,
        public name?: string,
        public account?: string,
        public password?: string,
        public status?: number,
        public userType?: number,
        public userIdentity?: number,
        public source?: number,
        public ipAddr?: string,
        public regTime?: Moment,
        public lastLoginTime?: Moment
    ) {}
}
