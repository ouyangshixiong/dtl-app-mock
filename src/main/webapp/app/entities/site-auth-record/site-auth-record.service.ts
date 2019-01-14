import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISiteAuthRecord } from 'app/shared/model/site-auth-record.model';

type EntityResponseType = HttpResponse<ISiteAuthRecord>;
type EntityArrayResponseType = HttpResponse<ISiteAuthRecord[]>;

@Injectable({ providedIn: 'root' })
export class SiteAuthRecordService {
    public resourceUrl = SERVER_API_URL + 'api/site-auth-records';

    constructor(protected http: HttpClient) {}

    create(siteAuthRecord: ISiteAuthRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(siteAuthRecord);
        return this.http
            .post<ISiteAuthRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(siteAuthRecord: ISiteAuthRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(siteAuthRecord);
        return this.http
            .put<ISiteAuthRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISiteAuthRecord>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISiteAuthRecord[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(siteAuthRecord: ISiteAuthRecord): ISiteAuthRecord {
        const copy: ISiteAuthRecord = Object.assign({}, siteAuthRecord, {
            createTime:
                siteAuthRecord.createTime != null && siteAuthRecord.createTime.isValid() ? siteAuthRecord.createTime.toJSON() : null,
            lastModifyTime:
                siteAuthRecord.lastModifyTime != null && siteAuthRecord.lastModifyTime.isValid()
                    ? siteAuthRecord.lastModifyTime.toJSON()
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
            res.body.forEach((siteAuthRecord: ISiteAuthRecord) => {
                siteAuthRecord.createTime = siteAuthRecord.createTime != null ? moment(siteAuthRecord.createTime) : null;
                siteAuthRecord.lastModifyTime = siteAuthRecord.lastModifyTime != null ? moment(siteAuthRecord.lastModifyTime) : null;
            });
        }
        return res;
    }
}
