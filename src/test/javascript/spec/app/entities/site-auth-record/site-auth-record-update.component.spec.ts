/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { SiteAuthRecordUpdateComponent } from 'app/entities/site-auth-record/site-auth-record-update.component';
import { SiteAuthRecordService } from 'app/entities/site-auth-record/site-auth-record.service';
import { SiteAuthRecord } from 'app/shared/model/site-auth-record.model';

describe('Component Tests', () => {
    describe('SiteAuthRecord Management Update Component', () => {
        let comp: SiteAuthRecordUpdateComponent;
        let fixture: ComponentFixture<SiteAuthRecordUpdateComponent>;
        let service: SiteAuthRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [SiteAuthRecordUpdateComponent]
            })
                .overrideTemplate(SiteAuthRecordUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SiteAuthRecordUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SiteAuthRecordService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SiteAuthRecord(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.siteAuthRecord = entity;
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
                    const entity = new SiteAuthRecord();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.siteAuthRecord = entity;
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
