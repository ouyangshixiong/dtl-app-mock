import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStoreAuthRecord } from 'app/shared/model/store-auth-record.model';

type EntityResponseType = HttpResponse<IStoreAuthRecord>;
type EntityArrayResponseType = HttpResponse<IStoreAuthRecord[]>;

@Injectable({ providedIn: 'root' })
export class StoreAuthRecordService {
    public resourceUrl = SERVER_API_URL + 'api/store-auth-records';

    constructor(protected http: HttpClient) {}

    create(storeAuthRecord: IStoreAuthRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(storeAuthRecord);
        return this.http
            .post<IStoreAuthRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(storeAuthRecord: IStoreAuthRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(storeAuthRecord);
        return this.http
            .put<IStoreAuthRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IStoreAuthRecord>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IStoreAuthRecord[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(storeAuthRecord: IStoreAuthRecord): IStoreAuthRecord {
        const copy: IStoreAuthRecord = Object.assign({}, storeAuthRecord, {
            createTime:
                storeAuthRecord.createTime != null && storeAuthRecord.createTime.isValid() ? storeAuthRecord.createTime.toJSON() : null,
            lastModifyTime:
                storeAuthRecord.lastModifyTime != null && storeAuthRecord.lastModifyTime.isValid()
                    ? storeAuthRecord.lastModifyTime.toJSON()
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
            res.body.forEach((storeAuthRecord: IStoreAuthRecord) => {
                storeAuthRecord.createTime = storeAuthRecord.createTime != null ? moment(storeAuthRecord.createTime) : null;
                storeAuthRecord.lastModifyTime = storeAuthRecord.lastModifyTime != null ? moment(storeAuthRecord.lastModifyTime) : null;
            });
        }
        return res;
    }
}
