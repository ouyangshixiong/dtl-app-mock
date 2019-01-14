/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { EnterpriseAuthRecordUpdateComponent } from 'app/entities/enterprise-auth-record/enterprise-auth-record-update.component';
import { EnterpriseAuthRecordService } from 'app/entities/enterprise-auth-record/enterprise-auth-record.service';
import { EnterpriseAuthRecord } from 'app/shared/model/enterprise-auth-record.model';

describe('Component Tests', () => {
    describe('EnterpriseAuthRecord Management Update Component', () => {
        let comp: EnterpriseAuthRecordUpdateComponent;
        let fixture: ComponentFixture<EnterpriseAuthRecordUpdateComponent>;
        let service: EnterpriseAuthRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [EnterpriseAuthRecordUpdateComponent]
            })
                .overrideTemplate(EnterpriseAuthRecordUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnterpriseAuthRecordUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnterpriseAuthRecordService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EnterpriseAuthRecord(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.enterpriseAuthRecord = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EnterpriseAuthRecord();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.enterpriseAuthRecord = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
