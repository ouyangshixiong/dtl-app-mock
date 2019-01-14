/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { RealnameAuthRecordUpdateComponent } from 'app/entities/realname-auth-record/realname-auth-record-update.component';
import { RealnameAuthRecordService } from 'app/entities/realname-auth-record/realname-auth-record.service';
import { RealnameAuthRecord } from 'app/shared/model/realname-auth-record.model';

describe('Component Tests', () => {
    describe('RealnameAuthRecord Management Update Component', () => {
        let comp: RealnameAuthRecordUpdateComponent;
        let fixture: ComponentFixture<RealnameAuthRecordUpdateComponent>;
        let service: RealnameAuthRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [RealnameAuthRecordUpdateComponent]
            })
                .overrideTemplate(RealnameAuthRecordUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RealnameAuthRecordUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RealnameAuthRecordService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RealnameAuthRecord(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.realnameAuthRecord = entity;
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
                    const entity = new RealnameAuthRecord();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.realnameAuthRecord = entity;
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
