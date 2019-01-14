import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRealnameAuthRecord } from 'app/shared/model/realname-auth-record.model';

type EntityResponseType = HttpResponse<IRealnameAuthRecord>;
type EntityArrayResponseType = HttpResponse<IRealnameAuthRecord[]>;

@Injectable({ providedIn: 'root' })
export class RealnameAuthRecordService {
    public resourceUrl = SERVER_API_URL + 'api/realname-auth-records';

    constructor(protected http: HttpClient) {}

    create(realnameAuthRecord: IRealnameAuthRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(realnameAuthRecord);
        return this.http
            .post<IRealnameAuthRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(realnameAuthRecord: IRealnameAuthRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(realnameAuthRecord);
        return this.http
            .put<IRealnameAuthRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRealnameAuthRecord>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRealnameAuthRecord[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(realnameAuthRecord: IRealnameAuthRecord): IRealnameAuthRecord {
        const copy: IRealnameAuthRecord = Object.assign({}, realnameAuthRecord, {
            createTime:
                realnameAuthRecord.createTime != null && realnameAuthRecord.createTime.isValid()
                    ? realnameAuthRecord.createTime.toJSON()
                    : null,
            lastModifyTime:
                realnameAuthRecord.lastModifyTime != null && realnameAuthRecord.lastModifyTime.isValid()
                    ? realnameAuthRecord.lastModifyTime.toJSON()
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
            res.body.forEach((realnameAuthRecord: IRealnameAuthRecord) => {
                realnameAuthRecord.createTime = realnameAuthRecord.createTime != null ? moment(realnameAuthRecord.createTime) : null;
                realnameAuthRecord.lastModifyTime =
                    realnameAuthRecord.lastModifyTime != null ? moment(realnameAuthRecord.lastModifyTime) : null;
            });
        }
        return res;
    }
}
