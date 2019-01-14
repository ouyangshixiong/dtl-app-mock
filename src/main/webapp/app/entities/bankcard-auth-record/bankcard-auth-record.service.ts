import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBankcardAuthRecord } from 'app/shared/model/bankcard-auth-record.model';

type EntityResponseType = HttpResponse<IBankcardAuthRecord>;
type EntityArrayResponseType = HttpResponse<IBankcardAuthRecord[]>;

@Injectable({ providedIn: 'root' })
export class BankcardAuthRecordService {
    public resourceUrl = SERVER_API_URL + 'api/bankcard-auth-records';

    constructor(protected http: HttpClient) {}

    create(bankcardAuthRecord: IBankcardAuthRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(bankcardAuthRecord);
        return this.http
            .post<IBankcardAuthRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(bankcardAuthRecord: IBankcardAuthRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(bankcardAuthRecord);
        return this.http
            .put<IBankcardAuthRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IBankcardAuthRecord>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IBankcardAuthRecord[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(bankcardAuthRecord: IBankcardAuthRecord): IBankcardAuthRecord {
        const copy: IBankcardAuthRecord = Object.assign({}, bankcardAuthRecord, {
            createTime:
                bankcardAuthRecord.createTime != null && bankcardAuthRecord.createTime.isValid()
                    ? bankcardAuthRecord.createTime.toJSON()
                    : null,
            lastModifyTime:
                bankcardAuthRecord.lastModifyTime != null && bankcardAuthRecord.lastModifyTime.isValid()
                    ? bankcardAuthRecord.lastModifyTime.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
            res.body.lastModifyTime = res.body.lastModifyTime != null ? moment(res.body.lastModifyTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((bankcardAuthRecord: IBankcardAuthRecord) => {
                bankcardAuthRecord.createTime = bankcardAuthRecord.createTime != null ? moment(bankcardAuthRecord.createTime) : null;
                bankcardAuthRecord.lastModifyTime =
                    bankcardAuthRecord.lastModifyTime != null ? moment(bankcardAuthRecord.lastModifyTime) : null;
            });
        }
        return res;
    }
}
