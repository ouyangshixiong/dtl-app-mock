import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnterpriseInfo } from 'app/shared/model/enterprise-info.model';

type EntityResponseType = HttpResponse<IEnterpriseInfo>;
type EntityArrayResponseType = HttpResponse<IEnterpriseInfo[]>;

@Injectable({ providedIn: 'root' })
export class EnterpriseInfoService {
    public resourceUrl = SERVER_API_URL + 'api/enterprise-infos';

    constructor(protected http: HttpClient) {}

    create(enterpriseInfo: IEnterpriseInfo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(enterpriseInfo);
        return this.http
            .post<IEnterpriseInfo>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(enterpriseInfo: IEnterpriseInfo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(enterpriseInfo);
        return this.http
            .put<IEnterpriseInfo>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEnterpriseInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEnterpriseInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(enterpriseInfo: IEnterpriseInfo): IEnterpriseInfo {
        const copy: IEnterpriseInfo = Object.assign({}, enterpriseInfo, {
            createTime:
                enterpriseInfo.createTime != null && enterpriseInfo.createTime.isValid() ? enterpriseInfo.createTime.toJSON() : null,
            lastModifyTime:
                enterpriseInfo.lastModifyTime != null && enterpriseInfo.lastModifyTime.isValid()
                    ? enterpriseInfo.lastModifyTime.toJSON()
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
            res.body.forEach((enterpriseInfo: IEnterpriseInfo) => {
                enterpriseInfo.createTime = enterpriseInfo.createTime != null ? moment(enterpriseInfo.createTime) : null;
                enterpriseInfo.lastModifyTime = enterpriseInfo.lastModifyTime != null ? moment(enterpriseInfo.lastModifyTime) : null;
            });
        }
        return res;
    }
}
