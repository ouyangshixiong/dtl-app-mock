import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProductRecord } from 'app/shared/model/product-record.model';

type EntityResponseType = HttpResponse<IProductRecord>;
type EntityArrayResponseType = HttpResponse<IProductRecord[]>;

@Injectable({ providedIn: 'root' })
export class ProductRecordService {
    public resourceUrl = SERVER_API_URL + 'api/product-records';

    constructor(protected http: HttpClient) {}

    create(productRecord: IProductRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(productRecord);
        return this.http
            .post<IProductRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(productRecord: IProductRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(productRecord);
        return this.http
            .put<IProductRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IProductRecord>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IProductRecord[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(productRecord: IProductRecord): IProductRecord {
        const copy: IProductRecord = Object.assign({}, productRecord, {
            createTime: productRecord.createTime != null && productRecord.createTime.isValid() ? productRecord.createTime.toJSON() : null,
            lastModifyTime:
                productRecord.lastModifyTime != null && productRecord.lastModifyTime.isValid()
                    ? productRecord.lastModifyTime.toJSON()
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
            res.body.forEach((productRecord: IProductRecord) => {
                productRecord.createTime = productRecord.createTime != null ? moment(productRecord.createTime) : null;
                productRecord.lastModifyTime = productRecord.lastModifyTime != null ? moment(productRecord.lastModifyTime) : null;
            });
        }
        return res;
    }
}
